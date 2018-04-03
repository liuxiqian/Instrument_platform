package com.mengyunzhi.measurement.Service;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.layout.LayoutResult;
import com.itextpdf.layout.renderer.DocumentRenderer;
import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.DepartmentRepository;
import com.mengyunzhi.measurement.repository.DepartmentTypeRepository;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentApplyRepository;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentCheckApplyRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.AccessException;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

import static com.mengyunzhi.measurement.entity.InstrumentEmploymentInfo.STATUS_NORMAL;

/**
 * Created by panjie on 17/11/21.
 * 强检器具检定申请
 */
@Service
public class MandatoryInstrumentCheckApplyServiceImpl implements MandatoryInstrumentCheckApplyService {
    private final static Logger logger = Logger.getLogger(MandatoryInstrumentCheckApplyServiceImpl.class.getName());

    public static final String DEST_DIR = "./target/checkApply/"; // 目录文件夹
    public static final String SRC_DO_NOT_HAVE_CHECK_DEPARTMENT = "./checkApply/doNotHaveCheckAbility.pdf"; // 没有检定能力的模板文件
    public static final String SRC_HAVE_CHECK_DEPARTMENT = "./checkApply/hadCheckAbility.pdf"; // 有检定能力的模板文件
    public static final String SEAL = "./checkApply/seal.gif"; // 印章文件

    @Autowired
    UserService userService;
    @Autowired
    FileService fileService;
    @Autowired
    ApplyFieldRecordService applyFieldRecordService;
    @Autowired
    MandatoryInstrumentApplyService mandatoryInstrumentApplyService;
    @Autowired
    MandatoryInstrumentCheckApplyRepository mandatoryInstrumentCheckApplyRepository;  // 检定申请
    @Autowired
    MandatoryInstrumentService mandatoryInstrumentService; // 强检器具
    @Autowired
    DeviceInstrumentService deviceInstrumentService; // 授权检定项目
    @Autowired
    MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository;  // 强捡器具申请
    @Autowired
    DepartmentTypeRepository departmentTypeRepository;      // 部门类型
    @Autowired
    DepartmentRepository departmentRepository;              // 部门
    @Autowired @Qualifier("DepartmentService")
    DepartmentService departmentService;
    @Autowired @Qualifier("WorkService") WorkService workService;       // 工作
    @Autowired FontService fontService; // 字体
    /**
     * 保存新申请
     * @param mandatoryInstrumentCheckApply
     * @return
     * panjie
     */
    @Override
    public Set<MandatoryInstrumentCheckApply> save(MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply) throws IllegalArgumentException {
        User currentUser = userService.getCurrentLoginUser();
        logger.debug("获取持久化数据");
        List<MandatoryInstrument> mandatoryInstrumentList = (List<MandatoryInstrument>)mandatoryInstrumentService.findAllByMandatoryInstrumentCollection(mandatoryInstrumentCheckApply.getMandatoryInstrumentSet());

        logger.debug("获取检定中的强检器具，去除超期的、状态非正常的、不属于当前用户的");
        this.getNotExpiredAndStatuNormalofCurrentUser(mandatoryInstrumentList);

        if (mandatoryInstrumentList.size() == 0) {
            logger.warn("符合要求的器具数为0，直接返回");
            return null;
        }

        logger.debug("初始化按检定机构分组及无检定能力的");
        HashMap<Department, Set<MandatoryInstrument>> departmentIdMandatoryInstrument = new HashMap<>();
        Set<MandatoryInstrument> doNotHasCheckAbilityMandatoryInstruments = new HashSet<>();

        logger.debug("自动进行检定能力的匹配，按检定机构进行分组");
        for(MandatoryInstrument mandatoryInstrument: mandatoryInstrumentList) {
            logger.debug("获取一个有检定能力的技术机构（先看区县有无检定能力，再看市有无检定能力，均无检定能返回null）");
            Department department = deviceInstrumentService.getCheckAbilityDepartmentByDistrictAndMandatoryInstrument(currentUser.getDepartment().getDistrict(), mandatoryInstrument);
            if (department == null) {
                logger.debug("无检定能力的，放入无检定能力列表中");
                doNotHasCheckAbilityMandatoryInstruments.add(mandatoryInstrument);
            } else {
                logger.debug("有检定能定的，对应放到hasMap的对应的key中");
                Set<MandatoryInstrument> mandatoryInstruments = departmentIdMandatoryInstrument.get(department);
                if (mandatoryInstruments == null) {
                    mandatoryInstruments = new HashSet<>();
                }
                mandatoryInstruments.add(mandatoryInstrument);

                departmentIdMandatoryInstrument.put(department, mandatoryInstruments);
            }
        }

        logger.debug("获取当前用户信息，并复制基础信息");
        MandatoryInstrumentCheckApply newMandatoryInstrumentCheckApply = new MandatoryInstrumentCheckApply();
        newMandatoryInstrumentCheckApply.setApplyTime(Calendar.getInstance());
        newMandatoryInstrumentCheckApply.setDepartment(currentUser.getDepartment());
        newMandatoryInstrumentCheckApply.setCreateUser(currentUser);
        newMandatoryInstrumentCheckApply.setRemarks(mandatoryInstrumentCheckApply.getRemarks());
        newMandatoryInstrumentCheckApply.setCheckPlace(mandatoryInstrumentCheckApply.getCheckPlace());

        logger.debug("按不同的检定机构，发送申请:");
        Set<MandatoryInstrumentCheckApply> mandatoryInstrumentCheckApplySet = new HashSet<>();
        departmentIdMandatoryInstrument.forEach((department ,mandatoryInstruments) -> {
            MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply1 = newMandatoryInstrumentCheckApply.clone();
            mandatoryInstrumentCheckApply1.setMandatoryInstrumentSet(mandatoryInstruments);
            mandatoryInstrumentCheckApply1.setCreateUser(currentUser);
            mandatoryInstrumentCheckApplyRepository.save(mandatoryInstrumentCheckApply1);
            mandatoryInstrumentCheckApplySet.add(mandatoryInstrumentCheckApply1);

            logger.debug("生成首个工作");
            Work firstWork = workService.addFirstWorkByApplyAndOpinion(mandatoryInstrumentCheckApply1, "发起申请");

            logger.debug("送下一默认审核部门");
            workService.sendDepartmentByPreWorkAndNextAuditDepartment(firstWork, department);

        });

        if (doNotHasCheckAbilityMandatoryInstruments.size() > 0) {
            logger.debug("如果存在没有检定能力的器具（未成功匹配检定机构），则发送申请给市管理部门:");
            MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply1 = newMandatoryInstrumentCheckApply.clone();
            mandatoryInstrumentCheckApply1.setMandatoryInstrumentSet(doNotHasCheckAbilityMandatoryInstruments);
            mandatoryInstrumentCheckApply1.setCreateUser(currentUser);
            mandatoryInstrumentCheckApplyRepository.save(mandatoryInstrumentCheckApply1);
            mandatoryInstrumentCheckApplySet.add(mandatoryInstrumentCheckApply1);

            logger.debug("生成首个工作");
            Work firstWork = workService.addFirstWorkByApplyAndOpinion(mandatoryInstrumentCheckApply1, "发起申请");

            logger.debug("送下一默认审核部门");
            workService.sendToNextDefaultDepartmentByPreWorkAndNextAuditDepartmentTypeNameAndNextAuditDepartmentDistrictTypeName(
                    firstWork, "管理部门", "市");
        }

        return mandatoryInstrumentCheckApplySet;
    }

    /**
     * 获取当前技术机构的分页数据
     * @param instrumentUserName 发起申请的器具用户
     * @param startApplyDate 开始时间
     * @param endApplyDate 截至时间
     * @param pageable 分页信息
     * @return
     * panjie
     */
    @Override
    public Page<CurrentWork> pageOfCurrentUser(String instrumentUserName, Date startApplyDate, Date endApplyDate, Pageable pageable) {
        User user = userService.getCurrentLoginUser();
        Calendar startCalendar = null;
        if (startApplyDate != null) {
            startCalendar = Calendar.getInstance();
            startCalendar.setTimeInMillis(startApplyDate.getTime());
        }

        Calendar endCalendar = null;
        if (endApplyDate != null) {
            endCalendar = Calendar.getInstance();
            endCalendar.setTimeInMillis(endApplyDate.getTime());
            endCalendar.add(Calendar.DAY_OF_YEAR, 1);   // 加1处理，更加符合前台的查询习惯
        }

        return currentWorkService.pageByAuditDepartmentAndApplyStartCalendarAndApplyEndCalendarAndApplyDepartmentAndApplyClass(
                user.getDepartment(),
                startCalendar,
                endCalendar,
                null,
                MandatoryInstrumentCheckApply.class,
                pageable
                );
    }


    @Override
    public void auditById(Long id, MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply) throws AccessException {
        logger.debug("获取持久实体");
        MandatoryInstrumentCheckApply oldMandatoryInstrumentCheckApply = mandatoryInstrumentCheckApplyRepository.findOne(id);

        logger.debug("判断权限");
        User currentUser = userService.getCurrentLoginUser();
        if (oldMandatoryInstrumentCheckApply.getCurrentWork().getWork().getAuditDepartment().getId() != currentUser.getDepartment().getId()) {
            throw new AccessException("您无更新权限");
        }

        logger.debug("更新实体信息");
        oldMandatoryInstrumentCheckApply.setReplyRemarks(mandatoryInstrumentCheckApply.getReplyRemarks());
        oldMandatoryInstrumentCheckApply.setPlannedCheckDate(mandatoryInstrumentCheckApply.getPlannedCheckDate());
        mandatoryInstrumentCheckApplyRepository.save(oldMandatoryInstrumentCheckApply);

        logger.debug("办结");
        mandatoryInstrumentApplyService.updateToDoneByApply(oldMandatoryInstrumentCheckApply);
        return;
    }

    /**
     * 根据检定申请id生成pdf文档
     * @param  checkApplyId    检定申请id
     * @return File            检定申请文件
     * @throws AccessException
     * @author zhangxishuo
     */
    @Override
    public File generatePdfApplyById(Long checkApplyId) throws AccessException {
        logger.debug("获取申请持久实体");
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply = this.findById(checkApplyId);

        logger.debug("判断用户权限");
        if (!this.validateUserByCheckApply(mandatoryInstrumentCheckApply)) {
            throw new AccessException("您没有下载的权限");
        }

        logger.debug("生成文档");
        File file = null;
        try {
            file = this.generatePdfByCheckApply(mandatoryInstrumentCheckApply);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("文档生成失败");
        }

        return file;
    }

    /**
     * 验证用户是否有检定申请的下载权限
     * @param mandatoryInstrumentCheckApply  检定申请
     * @return boolean    有无权限
     *   true   有权限
     *   false  无权限
     * @author zhangxishuo
     */
    protected boolean validateUserByCheckApply(MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply) {
        logger.debug("获取当前登录用户");
        User user = userService.getCurrentLoginUser();

        if (user.getDepartment().getId() == mandatoryInstrumentCheckApply.getDepartment().getId()) {
            logger.debug("用户有权限下载");
            return true;
        }

        logger.debug("用户无权限下载");
        return false;
    }

    /**
     * 根据检定申请生成pdf文档
     * @param mandatoryInstrumentCheckApply   检定申请
     * @return File        检定申请文档
     * @throws IOException
     * @author zhangxishuo
     */
    protected File generatePdfByCheckApply(MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply) throws IOException {
        logger.debug("新建目标文件及新建文件夹");
        File destFile = new File(DEST_DIR + String.valueOf(mandatoryInstrumentCheckApply.getId()) + "_0.pdf");
        destFile.getParentFile().mkdirs();

        String  src;
        boolean isAddSeal;

        if ((mandatoryInstrumentCheckApply.getCurrentWork().getWork().getAuditDepartment().getDepartmentType().getPinyin().equals("jishujigou"))) {
            logger.debug("检定部门为技术机构, 不加盖电子印章");
            src       = SRC_HAVE_CHECK_DEPARTMENT;
            isAddSeal = false;
        } else {
            logger.debug("检定部门设置为了管理部门（说明没有找到相应检定能力的技术机构）, 加盖电子印章");
            src       = SRC_DO_NOT_HAVE_CHECK_DEPARTMENT;
            isAddSeal = true;
        }

        File srcFile  = fileService.getResourcesFileByFilename(src);

        return this.generatePdfReportByCheckApplyAndSrcFileAndDestFileAndIsAddSeal(
                mandatoryInstrumentCheckApply,
                                        srcFile,
                                        destFile,
                                        isAddSeal);
    }

    /**
     * 通过检定申请、源文件、目标文件、是否加盖印章生成pdf文档
     * @param mandatoryInstrumentCheckApply       检定申请
     * @param srcFile          源文件
     * @param destFile         目标文件
     * @param isAddSeal        是否加盖印章
     * @return File             pdf文档
     * @throws IOException
     * @author zhangxishuo
     */
    protected File generatePdfReportByCheckApplyAndSrcFileAndDestFileAndIsAddSeal(MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply, File srcFile, File destFile, boolean isAddSeal) throws IOException {

        logger.debug("如果指定的目标文件所处的文件夹不存在，则建立文件夹");
        destFile.getParentFile().mkdirs();

        logger.debug("使用模板文件、目标文件，实例化PdfDocument");
        PdfReader reader = new PdfReader(srcFile.getAbsolutePath());
        PdfDocument pdfDoc = new PdfDocument(reader, new PdfWriter(destFile.getAbsolutePath()));

        logger.debug("实例化Document");
        Document doc = new Document(pdfDoc);

        logger.debug("添加 fields 字段信息");
        this.addFields(pdfDoc, mandatoryInstrumentCheckApply);

        logger.debug("实例化table, 并添加强检器具信息");
        Table table = this.pdfTableInit();
        this.addTableBody(table, mandatoryInstrumentCheckApply);
        this.appendTableAndSeal(doc, table, isAddSeal);    // 追加印章及table至pdf文档

        doc.close();
        return destFile;
    }

    /**
     * pdf文档中添加字段
     * @param pdfDoc     待添加字段的pdf文档
     * @param mandatoryInstrumentCheckApply 检定申请
     * @author zhangxishuo
     */
    protected void addFields(PdfDocument pdfDoc, MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply) throws IOException {

        logger.debug("获取表单中的字段并添加");
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
        PdfFont songFont = this.getSongFont();
        Map<String, PdfFormField> fields = form.getFormFields();
        Department applyDepartment = mandatoryInstrumentCheckApply.getDepartment();

        if (fields.get("departmentCode")    != null) {
            fields.get("departmentCode").setValue(applyDepartment.getCode()).setFont(songFont);
        }
        if (fields.get("departmentName")    != null) {
            fields.get("departmentName").setValue(applyDepartment.getName()).setFont(songFont);
        }
        if (fields.get("departmentName1")   != null) {
            fields.get("departmentName1").setValue(applyDepartment.getName()).setFont(songFont);
        }
        if (fields.get("departmentAddress") != null) {
            fields.get("departmentAddress").setValue(applyDepartment.getAddress()).setFont(songFont);
        }
        if (fields.get("contactNum")  != null) {
            fields.get("contactNum").setValue(mandatoryInstrumentCheckApply.getCreateUser().getMobile()).setFont(songFont); // 联系电话
        }
        if (fields.get("contactName") != null) {
            fields.get("contactName").setValue(mandatoryInstrumentCheckApply.getCreateUser().getName()).setFont(songFont); // 联系人
        }
        Calendar applyTime = mandatoryInstrumentCheckApply.getApplyTime();
        if (fields.get("applyUpdateDate") != null) {
            fields.get("applyUpdateDate").setValue(applyTime.get(applyTime.YEAR) + "年" + (applyTime.get(Calendar.MONTH) + 1)  + "月" + applyTime.get(applyTime.DATE) + "日").setFont(songFont);   // 申请日期
        }
        if (fields.get("size") != null) {
            fields.get("size").setValue(String.valueOf(mandatoryInstrumentCheckApply.getMandatoryInstrumentSet().size())).setFont(songFont);
        }
        if (fields.get("checkDepartmentName") != null) {
            fields.get("checkDepartmentName").setValue(mandatoryInstrumentCheckApply.getCurrentWork().getWork().getAuditDepartment().getName()).setFont(songFont);
        }
        if (fields.get("year") != null) {
            fields.get("year").setValue(String.valueOf(applyTime.get(Calendar.YEAR)));
        }
        if (fields.get("month") != null) {
            fields.get("month").setValue(String.valueOf(applyTime.get(Calendar.MONTH) + 1));
        }
        if (fields.get("day") != null) {
            fields.get("day").setValue(String.valueOf(applyTime.get(Calendar.DATE)));
        }
        form.flattenFields();

        return;
    }

    /**
     * 初始化表格
     * @return Table pdf中的表格
     */
    protected Table pdfTableInit() throws IOException {

        logger.debug("初始化表格及各列的宽度");
        float[] tableWidth = new float[]{1, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3};
        Table table = new Table(tableWidth);

        logger.debug("设置表格的宽度为100%");
        table.setWidthPercent(100);

        logger.debug("初始化表头项");
        String[] ths = {"序号", "器具名称", "规格型号", "测量范围", "准确度等级", "制造厂商名称", "出厂编号", "类别", "安装/使用地点", "器具编号", "申请编号", "用途"};
        if (tableWidth.length != ths.length) {
            throw new ExpressionException("table长度与设置的header长度不一致");
        }

        PdfFont songFont = this.getSongFont();

        logger.debug("依次添加表头");
        for (String th : ths) {
            table.addHeaderCell(th).setFont(songFont).setFontSize(10);
            table.addCell("");
        }

        return table;
    }

    /**
     * 添加表格内容
     * @param table       表格
     * @param mandatoryInstrumentCheckApply  检定申请
     */
    protected void addTableBody(Table table, MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply) throws IOException {

        PdfFont songFont = this.getSongFont();
        int i = 1;
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstrumentCheckApply.getMandatoryInstrumentSet()) {
            logger.debug("序号");
            table.addCell(String.valueOf(i));

            logger.debug("器具名称");
            table.addCell(mandatoryInstrument.getName()).setFont(songFont);   // 器具名称

            logger.debug("规格型号");
            table.addCell(mandatoryInstrument.getSpecificationName()).setFont(songFont);

            logger.debug("测量范围");
            table.addCell("开发中").setFont(songFont);
//            table.addCell(mandatoryInstrument.getMeasureScale()).setFont(songFont);

            logger.debug("准确度等级");
            table.addCell("开发中").setFont(songFont);
//            table.addCell(mandatoryInstrument.getAccuracy().getValue()).setFont(songFont);

            logger.debug("制造厂商名称");
            try {
                table.addCell(mandatoryInstrument.getGenerativeDepartment().getName()).setFont(songFont);
            } catch (Exception e) {
                table.addCell("");
            }

            logger.debug("出厂编号");
            table.addCell(mandatoryInstrument.getSerialNum()).setFont(songFont);

            logger.debug("类别");
            table.addCell(mandatoryInstrument.getInstrumentType().getName()).setFont(songFont);

            logger.debug("安装/使用地点");
            table.addCell(mandatoryInstrument.getFixSite()).setFont(songFont);

            logger.debug("器具编号");
            table.addCell(mandatoryInstrument.getId().toString()).setFont(songFont);

            logger.debug("申请编号");
            table.addCell(mandatoryInstrumentCheckApply.getId().toString()).setFont(songFont);

            logger.debug("用途");
            try {
                table.addCell(mandatoryInstrument.getPurpose().getName()).setFont(songFont);
            } catch (Exception e) {
                table.addCell("");
            }

            i++;
        }

        return;
    }

    /**
     * 为文档追加表格及印章
     * @param doc           pdf文档
     * @param table         表格
     * @param isAddSeal     是否加盖印章
     * @throws IOException
     */
    protected void appendTableAndSeal(Document doc, Table table, boolean isAddSeal) throws IOException {

        logger.debug("实例化印章");
        File imageFile = fileService.getResourcesFileByFilename(SEAL);
        Image img = new Image(ImageDataFactory.create(imageFile.getAbsolutePath()));
        img.scaleToFit(99, 99);

        logger.debug("追加印章及table");
        doc.setRenderer(new DocumentRenderer(doc) {
            @Override
            protected LayoutArea updateCurrentArea(LayoutResult overflowResult) {
                LayoutArea area = super.updateCurrentArea(overflowResult);
                if (area.getPageNumber() == 1) {
                    if (isAddSeal) {
                        logger.debug("加印章：第一页，设置印章位置为右下");
                        img.setFixedPosition(600, 160);
                        doc.add(img);
                    }

                    logger.debug("隐藏表格");
                    area.getBBox().setHeight(-100);
                } else {
                    if (isAddSeal) {
                        logger.debug("加印章：非第一页，设置印章位置为右上");
                        img.setFixedPosition(700, 470);
                        doc.add(img);
                    }

                    logger.debug("设置页面为横向A4");
                    doc.getPdfDocument().addNewPage(PageSize.A4.rotate());
                    if (area.getPageNumber() == 2) {
                        logger.debug("第二页，设置上边距");
                        area.getBBox().applyMargins(new Float(30), new Float(6), new Float(6), new Float(6), false);
                    }
                }

                return area;
            }
        });

        doc.add(table);

        return;
    }

    /**
     * 获取宋体
     * 不能使用itext自带的字体库，否则在处理特殊符号时，会报错。
     * 由于精确中使用的特殊符号较多
     * 在实际的使用中，还可能会遇到符号未渲染成功而异常的错误。后期尝试解决。
     * @return 宋体
     * @throws IOException
     * panjie
     */
    protected PdfFont getSongFont() throws IOException {
        File songTtcFile = fontService.getSongTtc();
        PdfFont songFont;
        try {
            /**
             * 该语句虽然能够用于生产环境，但是单元测试时会出现异常
             * 异常：找不到 xxxx,1文件  的确，我们并没有这个文件，加入,的原因，是为了匹配itext源码。
             * 但即使我们去掉后面的 ,1 在单元测试时，则会得到一个字体文件未注册的异常。
             * 而我们生产环境，却必须使用自定义的宋体来避免打印特殊字符时可能发生特殊字体渲染异常
             * 此处使用cry catch，当发生异常时，说明当面正在进行单元测试，则执行catch中的内容
             * panjie
             */
            songFont = PdfFontFactory.createFont(songTtcFile.getCanonicalPath() + ",1", PdfEncodings.IDENTITY_H, false);
            logger.info("使用自定义宋体来生成字体文件");
        } catch (Exception e) {
            /**
             * 本条语够能够在测试环境下生成宋体
             * 但如果渲染的文字中包括特殊字符，则会发生异常
             * 为了兼顾生产与测试环境，使用try catch来解决生产与测试环境不统一的问题
             */
            logger.warn("自定义字体文件生成失败，使用系统自带宋体");
            songFont = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", true);
        }
        return songFont;
    }

    @Override
    public MandatoryInstrumentCheckApply findById(Long id) {
        return mandatoryInstrumentCheckApplyRepository.findOne(id);
    }

    @Override
    public MandatoryInstrumentCheckApply getOneSavedCheckApply() {
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply = this.getOneUnSavedCheckApply();
        mandatoryInstrumentCheckApplyRepository.save(mandatoryInstrumentCheckApply);
        return mandatoryInstrumentCheckApply;
    }

    @Override
    public MandatoryInstrumentCheckApply getOneUnSavedCheckApply() {
        User user = userService.getOneSavedUser();
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply = new MandatoryInstrumentCheckApply();
        mandatoryInstrumentCheckApply.setDepartment(user.getDepartment());
        mandatoryInstrumentCheckApply.setCreateUser(user);

        /**
         * 设置时间为当前时间, 测试generatePdfApplyById时修改
         * update by zhangxishuo
         */
        Calendar calendar = Calendar.getInstance();
        mandatoryInstrumentCheckApply.setApplyTime(calendar);

        return mandatoryInstrumentCheckApply;
    }

    /**
     * 获取分页数据
     * @param pageable
     * @return
     */
    @Override
    public Page<Work> pageOfCurrentUser(Pageable pageable) {
        User user = userService.getCurrentLoginUser();
        Page<Work> works = currentWorkService.pageByAuditDepartmentAndApplyClassName(user.getDepartment(), MandatoryInstrumentCheckApply.CLASS_NAME, pageable);
        return works;
    }

    @Autowired CurrentWorkService currentWorkService;
    /**
     * 去除集合中符合一下条件的器具
     * 1.不属于当前用户的
     * 2.状态为不正常的
     * 3.超期的
     * @param mandatoryInstruments
     * @author chuhang
     */
    private void getNotExpiredAndStatuNormalofCurrentUser(List<MandatoryInstrument> mandatoryInstruments) {
        logger.debug("获取当前用户所在的部门");
        Department currentDepartment = userService.getCurrentLoginUser().getDepartment();

        logger.debug("遍历只留下符合条件的强检器具");
        Iterator<MandatoryInstrument> iterator = mandatoryInstruments.iterator();
        while (iterator.hasNext()) {
            MandatoryInstrument mandatoryInstrument = iterator.next();
            logger.debug("排除不属于当前登录用户的");
            if ((mandatoryInstrument.getDepartment() == null)
                    ||
                    (mandatoryInstrument.getDepartment().getId() != currentDepartment.getId())) {
                logger.debug("不属于当前登录用户");
                iterator.remove();
                continue;
            }

            logger.debug("排除状态为非正常的");
            if (mandatoryInstrument.getStatus() != STATUS_NORMAL) {
                logger.debug("状态为非正常的器具");
                iterator.remove();
                continue;
            }
        }
        return;
    }
}
