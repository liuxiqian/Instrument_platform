package com.mengyunzhi.measurement.Service;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
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
import com.mengyunzhi.measurement.exception.ValidationException;
import com.mengyunzhi.measurement.repository.ApplyTypeRepository;
import com.mengyunzhi.measurement.repository.DepartmentRepository;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentApplyRepository;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentRepository;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
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


/**
 * Created by liming on 17-7-15.
 * 强制检定器具申请
 */
@Service("MandatoryInstrumentApplyService")
public class MandatoryInstrumentApplyServiceImpl extends ApplyServiceImpl implements MandatoryInstrumentApplyService {
    private final static Logger logger = Logger.getLogger(MandatoryInstrumentApplyServiceImpl.class.getName());
    public static final String DEST_DIR = "./target/mandatoryInstrumentApply/"; // 目录文件夹
    public static final String SRC_DO_NOT_HAVE_CHECK_ABILITY = "./checkApply/doNotHaveCheckAbility.pdf"; // 没有检定能力的模板文件
    public static final String SRC_HAVE_CHECK_ABILITY = "./checkApply/hadCheckAbility.pdf"; // 有检定能力的模板文件
    public static final String SEAL = "./checkApply/seal.gif"; // 印章文件


    public static final String BACKED = "backed";               // 强检申请被退回
    public static final String Quxian = "quxian";               // 区县级技术机构检定的
    public static final String Shi = "shi";                     // 市级技术机构检定的
    public static final String Sheng = "sheng";                 // 省级技术机构检定的

    @Autowired
    protected UserService userService;
    @Autowired
    @Qualifier("WorkService")
    private WorkService workService;
    @Autowired
    private DepartmentRepository departmentRepository;      // 部门
    @Autowired
    private MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository; // 强检器具申请
    @Autowired
    private MandatoryInstrumentService mandatoryInstrumentService;
    @Autowired
    private MandatoryInstrumentRepository mandatoryInstrumentRepository;
    @Autowired
    private TechnicalInstitutionDepartmentService technicalInstitutionDepartmentService;    // 技术机构
    @Autowired
    DistrictService districtService; // 区域
    @Autowired
    private DeviceInstrumentService deviceInstrumentService;    // 授权检定项目
    @Autowired
    private SMSService smsService;                              // 手机短信
    @Autowired
    ManagementDepartmentBackedReasonService managementDepartmentBackedReasonService;// 退回理由
    @Autowired
    FileService fileService; // 文件服务
    @Autowired
    ApplyFieldRecordService applyFieldRecordService; // 申请字段记录
    @Autowired
    WorkflowNodeService workflowNodeService; // 工作流结点
    @Autowired
    ApplyTypeRepository applyTypeRepository;    // 申请类型
    @Autowired
    CurrentWorkService currentWorkService;       // 当前工作


    @Override
    public MandatoryInstrumentApply getOneSavedMandatoryInstrumentApply() {
        MandatoryInstrumentApply mandatoryInstrumentApply = this.getOneUnsavedMandatoryInstrumentApply();
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
        return mandatoryInstrumentApply;
    }

    @Override
    public MandatoryInstrumentApply getOneUnsavedMandatoryInstrumentApply() {
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        mandatoryInstrumentApply.setCreateUser(userService.getOneSavedUser());
        mandatoryInstrumentApply.setName("强检测试申请" + CommonService.getRandomStringByLength(10));
        return mandatoryInstrumentApply;
    }

    @Override
    public Page<Apply> getPageOfCurrentDepartment(Pageable pageable) {
        //取出当前用户
        User currentUser = userService.getCurrentLoginUser();
        //获取当前用户的部门
        Department department = currentUser.getDepartment();
        //获取当前部门的申请列表
        Page<Apply> page = workService.pageApplyByAuditDepartmentOfMandatoryInstrument(department, pageable);
        return page;
    }

    @Override
    public MandatoryInstrumentApply findById(Long id) {
        return mandatoryInstrumentApplyRepository.findOne(id);
    }

    @Override
    public MandatoryInstrumentApply save(MandatoryInstrumentApply mandatoryInstrumentApply) {
        logger.debug("获取基本信息");
        mandatoryInstrumentApply = this.cloneAndSetBaseInfo(mandatoryInstrumentApply);

        if (mandatoryInstrumentApply.getMandatoryInstruments().size() > 0) {
            logger.info("处理生产企业信息");
            User currentUser = userService.getCurrentLoginUser();
            for (MandatoryInstrument mandatoryInstrument : mandatoryInstrumentApply.getMandatoryInstruments()) {
                Department department = departmentRepository.findByName(mandatoryInstrument.getGenerativeDepartment().getName());
                if (department == null) {
                    department = departmentRepository.save(mandatoryInstrument.getGenerativeDepartment());
                }

                mandatoryInstrument.setGenerativeDepartment(department);
                mandatoryInstrument.setCreateUser(currentUser);
                mandatoryInstrument.setDepartment(currentUser.getDepartment());
            }

            logger.debug("设置申请类型，保存申请");
            mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);

            logger.debug("生成首个工作");
            Work firstWork = workService.addFirstWorkByApplyAndOpinion(mandatoryInstrumentApply, "发起申请");

            logger.debug("送下一默认审核部门");
            workService.sendToNextDefaultDepartmentByPreWork(firstWork);
        } else {
            logger.warn("未获取任何强检器具信息");
        }

        logger.debug("赋值并返回");
        return mandatoryInstrumentApply;
    }

    /**
     * 复制并设置基本的信息
     * 复制信息：强检器具列表\备注信息
     *
     * @param mandatoryInstrumentApply 强检器具申请
     * @return panjie
     */
    protected MandatoryInstrumentApply cloneAndSetBaseInfo(MandatoryInstrumentApply mandatoryInstrumentApply) {
        User currentUser = userService.getCurrentLoginUser();
        MandatoryInstrumentApply cloneMandatoryInstrumentApply = new MandatoryInstrumentApply();
        cloneMandatoryInstrumentApply.setMandatoryInstruments(mandatoryInstrumentApply.getMandatoryInstruments());
        cloneMandatoryInstrumentApply.setApplyTime(Calendar.getInstance());
        cloneMandatoryInstrumentApply.setCreateUser(currentUser);
        cloneMandatoryInstrumentApply.setDepartment(currentUser.getDepartment());
        cloneMandatoryInstrumentApply.setName(currentUser.getDepartment().getName() + "_强检器具备案申请");
        cloneMandatoryInstrumentApply.setRemarks(mandatoryInstrumentApply.getRemarks());
        return cloneMandatoryInstrumentApply;
    }

    @Override
    public MandatoryInstrumentApply update(MandatoryInstrumentApply mandatoryInstrumentApply) throws SecurityException {
        if (!this.isCanEditOfCurrentUser(mandatoryInstrumentApply)) {
            throw new SecurityException("您无此操作权限");
        }

        return mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
    }

    @Override
    public MandatoryInstrumentApply updateById(Long id, MandatoryInstrumentApply mandatoryInstrumentApply) throws ObjectNotFoundException {
        MandatoryInstrumentApply mandatoryInstrumentApply1 = mandatoryInstrumentApplyRepository.findOne(id);
        if (null == mandatoryInstrumentApply1) {
            throw new ObjectNotFoundException(404, "要更新的实体不存在");
        }
        mandatoryInstrumentApply.setId(id);
        return this.update(mandatoryInstrumentApply);
    }

    @Override
    public void addMandatoryInstrumentOfMandatoryInstrumentApply(MandatoryInstrument mandatoryInstrument, MandatoryInstrumentApply mandatoryInstrumentApply) {
        if (!this.isCanEditOfCurrentUser(mandatoryInstrumentApply)) {
            throw new SecurityException("您无此操作权限");
        }
        mandatoryInstrumentApply.addMandatoryInstrument(mandatoryInstrument);
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
    }

    @Override
    public void subMandatoryInstrumentOfMandatoryInstrumentApply(MandatoryInstrument mandatoryInstrument, MandatoryInstrumentApply mandatoryInstrumentApply) {
        if (!this.isCanEditOfCurrentUser(mandatoryInstrumentApply)) {
            throw new SecurityException("您无此操作权限");
        }
        for (MandatoryInstrument mandatoryInstrument1 : mandatoryInstrumentApply.getMandatoryInstruments()) {
            if (mandatoryInstrument.getId() == mandatoryInstrument1.getId()) {
                mandatoryInstrumentApply.getMandatoryInstruments().remove(mandatoryInstrument1);
            }
        }
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
    }

    @Override
    public void delete(MandatoryInstrumentApply mandatoryInstrumentApply) {
        if (!this.isCanEditOfCurrentUser(mandatoryInstrumentApply)) {
            throw new SecurityException("您无此操作权限");
        }
        mandatoryInstrumentApplyRepository.delete(mandatoryInstrumentApply);
    }

    @Override
    public boolean isCanEditOfCurrentUser(MandatoryInstrumentApply mandatoryInstrumentApply) {
        User user = userService.getCurrentLoginUser();
        if (mandatoryInstrumentApply.getCurrentWork().getWork().getAuditDepartment().getId() == user.getDepartment().getId()
                && mandatoryInstrumentApply.getDone() == false) {
            logger.info("当前部门为审核部门，且流程未办结");
            return true;
        } else {
            logger.info("当前部门非审核部或流程已办结");
            return false;
        }
    }

    /**
     * 计算检定能力
     * 先进行较验，较验成功，则调用computeCheckAbilityByMandatoryInstrumentApplyAndDepartment计算检定能力
     *
     * @param mandatoryInstrumentApplyId 强检申请id
     * @param departmentId               部门id
     * @return
     * @throws Exception
     */
    @Override
    public MandatoryInstrumentApply computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId(Long mandatoryInstrumentApplyId, Long departmentId) throws Exception {
        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyRepository.findOne(mandatoryInstrumentApplyId);
        if (null == mandatoryInstrumentApply) {
            throw new ObjectNotFoundException(404, "未找到相关的强制检定申请实体");
        }

        Department department = departmentRepository.findOne(departmentId);
        if (null == department) {
            throw new ObjectNotFoundException(404, "未找到相关的部门实体");
        }
        return this.computeCheckAbilityByMandatoryInstrumentApplyAndDepartment(mandatoryInstrumentApply, department);
    }

    /**
     * 计算某个部门对某个强检器具申请上的所有强检器具是否具备检定能力
     *
     * @param mandatoryInstrumentApply 强检申请
     * @param department               部门
     * @return
     * @throws Exception
     */
    @Override
    public MandatoryInstrumentApply computeCheckAbilityByMandatoryInstrumentApplyAndDepartment(MandatoryInstrumentApply mandatoryInstrumentApply, Department department) throws Exception {
        mandatoryInstrumentService.computerCheckAbilityByDepartmentIdOfMandatoryInstruments(department.getId(), mandatoryInstrumentApply.getMandatoryInstruments());
        return mandatoryInstrumentApply;
    }

    /**
     * 获取分组以后的强检器具信息
     * 分组依据：通过审核指定检定部门的，通过审核未指定检定部门的
     *
     * @param mandatoryInstrumentApply 强检申请
     * @return 以Department(部门)为key，以Set<MandatoryInstrument>为值的Map
     */
    public Map<Department, Set<MandatoryInstrument>> getGroupMandatoryInstrumentByMandatoryInstrumentApply(MandatoryInstrumentApply mandatoryInstrumentApply
    ) {
        logger.info("获取强检器具并按检定部门分组");
        Map<Department, Set<MandatoryInstrument>> mandatoryInstruments = new HashMap<>();
        Department nullDepartment = new Department();
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstrumentApply.getMandatoryInstruments()) {
            if (mandatoryInstrument.getStatus() != InstrumentEmploymentInfo.STATUS_BACKED) {
                logger.info("器具没有被退回");
                if (null == mandatoryInstrument.getLastCheckDepartment()) {
                    logger.info("未指定检定部门");
                    if (null == mandatoryInstruments.get(nullDepartment)) {
                        logger.info("首次加入");
                        Set<MandatoryInstrument> mandatoryInstrumentSet = new HashSet<>();
                        mandatoryInstruments.put(nullDepartment, mandatoryInstrumentSet);
                    }
                    mandatoryInstruments.get(nullDepartment).add(mandatoryInstrument);
                } else {
                    logger.info("指定了的检定部门");
                    if (null == mandatoryInstruments.get(mandatoryInstrument.getLastCheckDepartment())) {
                        logger.info("首次加入");
                        Set<MandatoryInstrument> mandatoryInstrumentSet = new HashSet<>();
                        mandatoryInstruments.put(mandatoryInstrument.getLastCheckDepartment(), mandatoryInstrumentSet);
                    }
                    logger.info("将当前器具送入set集合中");
                    mandatoryInstruments.get(mandatoryInstrument.getLastCheckDepartment()).add(mandatoryInstrument);
                }
            }
        }
        return mandatoryInstruments;
    }


    /**
     * 通过申请ID生成无检定能力的pdf文档, 并返回文档
     *
     * @param id
     * @return
     * @throws IOException panjie
     */
    @Override
    public File generateDoNotHaveCheckAbilityPdfReportByApplyId(Long id) throws IOException, AccessException {
        logger.info("获取基本信息");
        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyRepository.findOne(id);
        if (!this.isCanExportPdfOfCurrentUser(mandatoryInstrumentApply)) {
            throw new AccessException("当前申请未办结，或您不是当前申请的发起部门");
        }
        logger.info("调用第二种统计方法，只统计出通过审核但却未指定检定技术机构的器具");
        ArrayList<MandatoryInstrument> mandatoryInstruments = this.getArrayListWithAlgorithm2ByMandatoryInstrumentApply(mandatoryInstrumentApply);

        logger.info("新建目标文件及新建文件夹");
        File destFile = new File(DEST_DIR + String.valueOf(mandatoryInstrumentApply.getId()) + "_0.pdf");
        destFile.getParentFile().mkdirs();

        logger.info("使用目标文件(源文件），实例化一个PdfReader");
        File srcFile = fileService.getResourcesFileByFilename(SRC_DO_NOT_HAVE_CHECK_ABILITY);

        logger.info("生成检定报告");
        return this.generatePdfReportByMandatoryInstrumentApplyAndMandatoryInstrumentListAndSrcFileAndDestFileAndCheckDepartmentAndIsAddSeal(
                mandatoryInstrumentApply,
                mandatoryInstruments,
                srcFile,
                destFile,
                null,
                false);
    }

    /**
     * 生成PDF申请报告
     * 有检定机构的说明本区域具有检定能力，则不加盖电子印章
     *
     * @param id                申请ID
     * @param checkDepartmentId 指定的检定机构ID
     * @return
     */
    @Override
    public File generatePdfReportByApplyIdAndCheckDepartmentId(Long id, Long checkDepartmentId) throws AccessException, IOException {
        logger.info("获取申请");
        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyRepository.findOne(id);

        logger.info("较验权限");
        if (!this.isCanExportPdfOfCurrentUser(mandatoryInstrumentApply)) {
            throw new AccessException("当前申请未办结，或您并不处于申请发起部门");
        }

        logger.info("查找出传入指定检定机构对应的检定器具");
        List<MandatoryInstrument> mandatoryInstrumentList = this.getMandatoryInstrumentListByCheckDepartmentId(mandatoryInstrumentApply.getMandatoryInstruments(), checkDepartmentId);

        logger.info("指定目录文件，模板文件");
        File destFile = new File(DEST_DIR + String.valueOf(mandatoryInstrumentApply.getId()) + "_" + checkDepartmentId + ".pdf");   // 以申请id_检定机构id.pdf 来生成全名规则
        File srcFile = fileService.getResourcesFileByFilename(SRC_HAVE_CHECK_ABILITY);
        Department checkDepartment = departmentRepository.findOne(checkDepartmentId);

        logger.info("生成检定报告");
        return this.generatePdfReportByMandatoryInstrumentApplyAndMandatoryInstrumentListAndSrcFileAndDestFileAndCheckDepartmentAndIsAddSeal(
                mandatoryInstrumentApply,
                mandatoryInstrumentList,
                srcFile,
                destFile,
                checkDepartment,
                false);
    }


    /**
     * 生成PDF检定申请报告
     *
     * @param mandatoryInstrumentApply 强检申请
     * @param mandatoryInstrumentList  报告中的器具列表
     * @param srcFile                  报告模板文件
     * @param destFile                 报告目标文件
     * @param checkDepartment          指定的检定机构
     * @param isAddSeal                是否加盖电子印章
     * @return
     * @throws IOException panjie
     */
    protected File generatePdfReportByMandatoryInstrumentApplyAndMandatoryInstrumentListAndSrcFileAndDestFileAndCheckDepartmentAndIsAddSeal(
            MandatoryInstrumentApply mandatoryInstrumentApply,
            List<MandatoryInstrument> mandatoryInstrumentList,
            File srcFile,
            File destFile,
            Department checkDepartment,
            boolean isAddSeal) throws IOException {
        logger.info("如果指定的目标文件所处的文件夹不存在，则建立文件夹");
        destFile.getParentFile().mkdirs();

        logger.info("使用模板文件、目标文件，实例化PdfDocument");
        PdfReader reader = new PdfReader(srcFile.getAbsolutePath());
        PdfDocument pdfDoc = new PdfDocument(reader, new PdfWriter(destFile.getAbsolutePath()));

        logger.info("实例化Document");
        Document doc = new Document(pdfDoc);

        logger.info("添加 fields 字段信息");
        this.addFields(pdfDoc, mandatoryInstrumentApply, mandatoryInstrumentList, checkDepartment);

        logger.info("实例化table, 并添加强检器具信息");
        Table table = this.pdfTableInit();
        this.addTableBody(table, mandatoryInstrumentList);
        this.appendTableAndSeal(doc, table, isAddSeal);    // 追加印章及table至pdf文档

        doc.close();
        return destFile;
    }

    /**
     * 挑选出指定为某个技术机构的强检器具列表
     *
     * @param mandatoryInstruments 强检器具
     * @param checkDepartmentId    技术机构ID
     * @return panjie
     */
    protected List<MandatoryInstrument> getMandatoryInstrumentListByCheckDepartmentId(
            Collection<MandatoryInstrument> mandatoryInstruments, Long checkDepartmentId) {
        List<MandatoryInstrument> mandatoryInstrumentList = new ArrayList<>();
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstruments) {
            if (mandatoryInstrument.getLastCheckDepartment() != null
                    && mandatoryInstrument.getLastCheckDepartment().getId() == checkDepartmentId) {
                mandatoryInstrumentList.add(mandatoryInstrument);
            }
        }
        return mandatoryInstrumentList;
    }

    /**
     * todo: 是否可以导出PDF
     * 增加两个权限判断：
     * 1. 当前申请状态为办结时
     * 2. 当前登录部门为申请部门时
     *
     * @param mandatoryInstrumentApply 强检器具申请
     * @return
     */
    @Override
    public Boolean isCanExportPdfOfCurrentUser(MandatoryInstrumentApply mandatoryInstrumentApply) {
        logger.info("获取当前登录部门");
        Department currentDepartment = userService.getCurrentLoginUser().getDepartment();

        logger.info("1. 当前申请状态为办结时");
        if (mandatoryInstrumentApply != null && mandatoryInstrumentApply.getDone() == Boolean.TRUE) {
            logger.info("2. 当前登录部门为申请部门时");
            if (currentDepartment.getId() == mandatoryInstrumentApply.getDepartment().getId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 追加table及印章
     *
     * @param doc
     * @param table
     * @param isAddSeal 是否加盖印章
     * @throws IOException panjie
     */
    private void appendTableAndSeal(Document doc, Table table, boolean isAddSeal) throws IOException {
        logger.info("实例化印章");
        File imageFile = fileService.getResourcesFileByFilename(SEAL);
        Image img = new Image(ImageDataFactory.create(imageFile.getAbsolutePath()));
        img.scaleToFit(99, 99);

        logger.info("追加印章及table");
        doc.setRenderer(new DocumentRenderer(doc) {
            @Override
            protected LayoutArea updateCurrentArea(LayoutResult overflowResult) {
                LayoutArea area = super.updateCurrentArea(overflowResult);
                if (area.getPageNumber() == 1) {
                    if (isAddSeal) {
                        logger.info("加印章：第一页，设置印章位置为右下");
                        img.setFixedPosition(600, 160);
                        doc.add(img);
                    }

                    logger.info("隐藏表格");
                    area.getBBox().setHeight(-100);
                } else {
                    if (isAddSeal) {
                        logger.info("加印章：非第一页，设置印章位置为右上");
                        img.setFixedPosition(700, 470);
                        doc.add(img);
                    }

                    logger.info("设置页面为横向A4");
                    doc.getPdfDocument().addNewPage(PageSize.A4.rotate());
                    if (area.getPageNumber() == 2) {
                        logger.info("第二页，设置上边距");
                        area.getBBox().applyMargins(new Float(30), new Float(6), new Float(6), new Float(6), false);
                    }
                }

                return area;
            }
        });

        doc.add(table);
    }

    /**
     * 添加表格的主体信息
     *
     * @param table
     * @param mandatoryInstruments 强检器具列表
     *                             panjie
     */
    private void addTableBody(Table table, List<MandatoryInstrument> mandatoryInstruments) throws IOException {
        PdfFont songFont = this.getSongFont();
        int i = 1;
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstruments) {
            logger.info("序号");
            table.addCell(String.valueOf(i));

            logger.info("器具名称");
            table.addCell(mandatoryInstrument.getName()).setFont(songFont);   // 器具名称

            logger.info("规格型号");
            table.addCell(mandatoryInstrument.getSpecificationName()).setFont(songFont);

            logger.info("测量范围");
            table.addCell(mandatoryInstrument.getMeasureScale()).setFont(songFont);

            logger.info("准确度等级");
            table.addCell(mandatoryInstrument.getAccuracy().getValue()).setFont(songFont);

            logger.info("制造厂商名称");
            try {
                table.addCell(mandatoryInstrument.getGenerativeDepartment().getName()).setFont(songFont);
            } catch (Exception e) {
                table.addCell("");
            }

            logger.info("出厂编号");
            table.addCell(mandatoryInstrument.getSerialNum()).setFont(songFont);

            logger.info("类别");
            table.addCell(mandatoryInstrument.getInstrumentType().getName()).setFont(songFont);

            logger.info("安装/使用地点");
            table.addCell(mandatoryInstrument.getFixSite()).setFont(songFont);

            logger.info("用途");
            try {
                table.addCell(mandatoryInstrument.getPurpose().getName()).setFont(songFont);
            } catch (Exception e) {
                table.addCell("");
            }

            i++;
        }
    }

    /**
     * 获取宋体
     * <p>
     * 每次都需要返回一个新的字体，该字体属于某一个document。多个document不能够共用，否则会报错。
     *
     * @return
     * @throws IOException panjie
     */
    private PdfFont getSongFont() throws IOException {
        return PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", true);
    }

    /**
     * 初始化table
     *
     * @return
     * @throws IOException panjie
     */
    private Table pdfTableInit() throws IOException {
        logger.info("初始化表格及各列的宽度");
        float[] tableWidth = new float[]{1, 4, 4, 4, 4, 4, 4, 4, 4, 3};
        Table table = new Table(tableWidth);

        logger.info("设置表格的宽度为100%");
        table.setWidthPercent(100);

        logger.info("初始化表头项");
        String[] ths = {"序号", "器具名称", "规格型号", "测量范围", "准确度等级", "制造厂商名称", "出厂编号", "类别", "安装/使用地点", "用途"};
        if (tableWidth.length != ths.length) {
            throw new ExpressionException("table长度与设置的header长度不一致");
        }

        PdfFont songFont = this.getSongFont();

        logger.info("依次添加表头");
        for (String th : ths) {
            table.addHeaderCell(th).setFont(songFont).setFontSize(10);
            table.addCell("");
        }

        return table;
    }

    /**
     * 添加字段信息
     *
     * @param pdfDoc                   PdfDocument
     * @param mandatoryInstrumentApply 强检申请
     * @param mandatoryInstruments     强检器具列表
     *                                 panjie
     */
    private void addFields(PdfDocument pdfDoc, MandatoryInstrumentApply mandatoryInstrumentApply, List<MandatoryInstrument> mandatoryInstruments, Department checkDepartment) throws IOException {
        logger.info("获取表单中的字段并添加");
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
        PdfFont songFont = this.getSongFont();
        Map<String, PdfFormField> fields = form.getFormFields();
        Department applyDepartment = mandatoryInstrumentApply.getDepartment();
        if (fields.get("departmentCode") != null) {
            fields.get("departmentCode").setValue(applyDepartment.getCode()).setFont(songFont);
        }
        if (fields.get("departmentName") != null) {
            fields.get("departmentName").setValue(applyDepartment.getName()).setFont(songFont);
        }
        if (fields.get("departmentName1") != null) {
            fields.get("departmentName1").setValue(applyDepartment.getName()).setFont(songFont);
        }
        if (fields.get("departmentAddress") != null) {
            fields.get("departmentAddress").setValue(applyDepartment.getAddress()).setFont(songFont);
        }
        if (fields.get("contactNum") != null) {
            fields.get("contactNum").setValue(applyFieldRecordService.getValueByApplyAndApplyFieldName(mandatoryInstrumentApply, MandatoryInstrumentApplyService.FIELD_CONTACT_NUMBER)).setFont(songFont);// 联系电话
        }
        if (fields.get("contactName") != null) {
            fields.get("contactName").setValue(applyFieldRecordService.getValueByApplyAndApplyFieldName(mandatoryInstrumentApply, MandatoryInstrumentApplyService.FIELD_CONTACT_NAME)).setFont(songFont); // 联系人
        }
        Calendar applyTime = mandatoryInstrumentApply.getApplyTime();
        if (fields.get("applyUpdateDate") != null) {
            fields.get("applyUpdateDate").setValue(applyTime.get(applyTime.YEAR) + "年" + applyTime.get(applyTime.MONTH) + "月" + applyTime.get(applyTime.DATE) + "日").setFont(songFont);   // 申请日期
        }
        if (fields.get("size") != null) {
            fields.get("size").setValue(String.valueOf(mandatoryInstruments.size())).setFont(songFont);
        }
        if (checkDepartment != null && fields.get("checkDepartmentName") != null) {
            fields.get("checkDepartmentName").setValue(checkDepartment.getName()).setFont(songFont);
        }
        if (fields.get("year") != null) {
            fields.get("year").setValue(String.valueOf(mandatoryInstrumentApply.getApplyTime().get(Calendar.YEAR)));
        }
        if (fields.get("month") != null) {
            fields.get("month").setValue(String.valueOf(mandatoryInstrumentApply.getApplyTime().get(Calendar.MONTH)));
        }
        if (fields.get("day") != null) {
            fields.get("day").setValue(String.valueOf(mandatoryInstrumentApply.getApplyTime().get(Calendar.DATE)));
        }
        form.flattenFields();
    }

    /**
     * 第二种统计方法：只统计出状态为通过但未指定检定部门的器具。
     * 分别取出强检申请中的强检器具，并对状态和检定部门进行判断。符合条件的，加入到生成word文档使用的map数据中
     *
     * @param map                      <String, Object>
     * @param mandatoryInstrumentApply 强检器具申请
     * @return panjie
     */
    public ArrayList<Object> getTableDataAndSetStatisticsDataWithAlgorithm2ByMandatoryInstruments(Map<String, Object> map, MandatoryInstrumentApply mandatoryInstrumentApply) {
        ArrayList<MandatoryInstrument> mandatoryInstrumentList = this.getArrayListWithAlgorithm2ByMandatoryInstrumentApply(mandatoryInstrumentApply);

        ArrayList<Object> tableData = new ArrayList<Object>() {{
            Integer i = 0;
            for (MandatoryInstrument mandatoryInstrument : mandatoryInstrumentList) {
                add(MandatoryInstrumentApplyService.addTableColumnDataByIndexAndMandatoryInstrument(i, mandatoryInstrument));
                i++;
            }
        }};

        // 设置总个数（一共多少个需要指定检定部门的器具）
        map.put("totalShouldByAssignCheckDepartmentCount", tableData.size());
        return tableData;
    }

    /**
     * 第二种统计方法：只统计出状态为通过但未指定检定部门的器具
     * 分别取出强检申请中的强检器具，并对状态和检定部门进行判断。符合条件的，加入列表
     *
     * @param mandatoryInstrumentApply
     * @return panjie
     */
    protected ArrayList<MandatoryInstrument> getArrayListWithAlgorithm2ByMandatoryInstrumentApply(MandatoryInstrumentApply mandatoryInstrumentApply) {
        ArrayList<MandatoryInstrument> mandatoryInstrumentList = new ArrayList<MandatoryInstrument>() {{
            for (MandatoryInstrument mandatoryInstrument : mandatoryInstrumentApply.getMandatoryInstruments()) {
                if (mandatoryInstrument.getStatus() == MandatoryInstrument.STATUS_NORMAL && mandatoryInstrument.getLastCheckDepartment() == null) {
                    add(mandatoryInstrument);
                }
            }
        }};
        return mandatoryInstrumentList;
    }

    /**
     * 第一种器具分类算法：分辊取出检定技术为：区县技术机构、市技术机构、省技术机构的器具列表。
     * 并计算出统计信息（三种类型的器具分别多少个，起始点为多少，结束点为多少)
     *
     * @param map
     * @param mandatoryInstrumentApply 强检申请
     * @return
     */
    public ArrayList<Object> getTableDataAndSetStatisticsDataWithAlgorithm1ByMandatoryInstruments(Map<String, Object> map, MandatoryInstrumentApply mandatoryInstrumentApply) {

        // 初始化统计数据
        final Integer[] quxianBeginIndex = {0};                     // 区县检定开始序号
        final Integer[] quxianEndIndex = {0};                       // 区县检定结束序号
        final Integer[] shiBeginIndex = {0};                        // 市检定机构开始序号
        final Integer[] shiEndIndex = {0};                          // 市检定机构结构序号
        final Integer[] otherBeginIndex = {0};                      // 其它没有检定能力的器具开始的序号
        final Integer[] otherEndIndex = {0};                        // 其它没有检定能力 结束的序号
        final String[] qunxianTechnicalInstitutionName = {""};      // 区县检定机构的名称

        logger.info("获取分组信息");
        Map<Department, Set<MandatoryInstrument>> mandatoryInstruments = this.getGroupMandatoryInstrumentByMandatoryInstrumentApply(mandatoryInstrumentApply);
        logger.info("写入表格的主体信息");
        ArrayList<Object> tableData = new ArrayList<Object>() {{
            Integer i = 1;
            for (Map.Entry<Department, Set<MandatoryInstrument>> entry : mandatoryInstruments.entrySet()) {
                logger.info("增加起始ID的计数器");
                Integer j = i;
                for (MandatoryInstrument mandatoryInstrument1 : entry.getValue()) {
                    add(MandatoryInstrumentApplyService.addTableColumnDataByIndexAndMandatoryInstrument(i, mandatoryInstrument1));
                    i++;
                }
                if (entry.getKey().getId() == null) {
                    logger.info("无检定能力，需要向上级技术机构送审的器具");
                    otherBeginIndex[0] = j;
                    otherEndIndex[0] = i - 1;
                } else if (entry.getKey().getDistrict().getDistrictType().getName().equals("市")) {
                    logger.info("市级技术机构送审的器具");
                    shiBeginIndex[0] = j;
                    shiEndIndex[0] = i - 1;
                } else if (entry.getKey().getDistrict().getDistrictType().getPinyin().equals("quxian")) {
                    logger.info("区\\县技术机构送审的器具");
                    quxianBeginIndex[0] = j;
                    quxianEndIndex[0] = i - 1;
                    qunxianTechnicalInstitutionName[0] = entry.getKey().getName();
                }
            }
        }};

        logger.info("设置统计信息");
        map.put("quxianBeginIndex", quxianBeginIndex[0] == 0 ? "-" : quxianBeginIndex[0].toString());
        map.put("quxianEndIndex", quxianEndIndex[0] == 0 ? "-" : quxianEndIndex[0].toString());
        map.put("shiBeginIndex", shiBeginIndex[0] == 0 ? "-" : shiBeginIndex[0].toString());
        map.put("shiEndIndex", shiEndIndex[0] == 0 ? "-" : shiEndIndex[0].toString());
        map.put("otherBeginIndex", otherBeginIndex[0] == 0 ? "-" : otherBeginIndex[0]);
        map.put("otherEndIndex", otherEndIndex[0] == 0 ? "-" : otherEndIndex[0]);
        map.put("qunxianTechnicalInstitutionName", qunxianTechnicalInstitutionName[0] == "" ? "-" : qunxianTechnicalInstitutionName[0]);
        return tableData;
    }



    @Override
    public MandatoryInstrumentApply getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId(Long id) {
        logger.info("获取这个申请");
        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyRepository.findOne(id);

        logger.info("获取申请对应的部门");
        Department department = mandatoryInstrumentApply.getDepartment();

        logger.info("获取部门所在区域及父区域所有技术机构");
        List<Department> technicalInstitutionDepartments = technicalInstitutionDepartmentService.findDistrictAndParentDistrictAllByDepartment(department);

        logger.info("遍历申请上的强检器具");
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstrumentApply.getMandatoryInstruments()) {

            logger.info("clone并遍历所有技术机构，并获取技术机构是否有检定能力。");
            List cloneTechnicalInstitutionDepartments = new ArrayList();
            for (Department department1 : technicalInstitutionDepartments) {
                Department cloneDepartment = department1.clone();
                logger.info("设置器具的所有检定机构");
                cloneDepartment.setCheckAbility(
                        deviceInstrumentService.getCheckAbilityByMandatoryInstrumentAndDepartment(mandatoryInstrument, department1));
                cloneTechnicalInstitutionDepartments.add(cloneDepartment);
            }
            mandatoryInstrument.setCheckTechnicalInstitutionDepartments(cloneTechnicalInstitutionDepartments);
        }

        return mandatoryInstrumentApply;
    }

    /**
     * 批量通过审核
     *
     * @param mandatoryInstrumentSet     强检器具set
     * @param mandatoryInstrumentApplyId 申请ID
     */
    @Override
    public void batchPassByMandatoryInstrumentsAndMandatoryInstrumentApplyId(Collection<MandatoryInstrument> mandatoryInstrumentSet, Long mandatoryInstrumentApplyId) {
        List<MandatoryInstrument> mandatoryInstrumentList;
        try {
             mandatoryInstrumentList = this.validateAccessForBatchPassOrBack(mandatoryInstrumentSet, mandatoryInstrumentApplyId);
        } catch (ValidationException e) {
            logger.warn("权限较验错误：" + e.getMessage());
            return;
        }

        logger.info("循环进行审核通过");
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstrumentList) {

            logger.info("审核通过, 但不持久化");
            this.passMandatoryInstrument(mandatoryInstrument);
        }

        logger.info("持久化");
        mandatoryInstrumentRepository.save(mandatoryInstrumentList);
        return;
    }

    @Override
    public void batchBackByMandatoryInstrumentsAndReasonAndApplyId(Collection<MandatoryInstrument> mandatoryInstrumentsList, String reason, Long applyId) {
        List<MandatoryInstrument> mandatoryInstrumentList;
        try {
            mandatoryInstrumentList = this.validateAccessForBatchPassOrBack(mandatoryInstrumentsList, applyId);
        } catch (ValidationException e) {
            logger.warn("权限较验错误：" + e.getMessage());
            return;
        }

        logger.info("获取一个退回理由");
        ManagementDepartmentBackedReason managementDepartmentBackedReason =
                managementDepartmentBackedReasonService.getOnePersistenceManagementDepartmentBackedReasonByReason(reason);

        logger.info("循环进行审核通过");
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstrumentList) {

            logger.info("审核通过, 但不持久化");
            this.backMandatoryInstrument(mandatoryInstrument);

            logger.info("设置退回理由");
            mandatoryInstrument.setManagementDepartmentBackedReason(managementDepartmentBackedReason);
        }

        logger.info("持久化");
        mandatoryInstrumentRepository.save(mandatoryInstrumentList);
        return;
    }

    /**
     * 较验当前用户是否有批量退回或备案的权限
     * @param mandatoryInstrumentSet 强检器具
     * @param mandatoryInstrumentApplyId 强检申请
     * @return List<MandatoryInstrument> 被筛选过的器具列表
     * @throws ValidationException
     * @author panjie
     */
    protected  List<MandatoryInstrument> validateAccessForBatchPassOrBack(
            Collection<MandatoryInstrument> mandatoryInstrumentSet,
            Long mandatoryInstrumentApplyId) throws ValidationException{
        User currentLoginUser = userService.getCurrentLoginUser();
        if (!currentLoginUser.getDepartment().getDepartmentType().getName().equals("管理部门")) {
            throw new ValidationException("当前用户并不是管理部门，无指定权限");
        }

        logger.info("获取申请信息，并判断是否未办结，及在、待办部门是否为当前部门");
        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyRepository.findOne(mandatoryInstrumentApplyId);
        if (mandatoryInstrumentApply.getDone()) {
            throw new ValidationException("当前申请已办结，无法重新审核");
        }

        Work currentWork = workService.findLastWorkByApply(mandatoryInstrumentApply);
        if (!currentWork.getAuditDepartment().getId().equals(currentLoginUser.getDepartment().getId())) {
            throw new ValidationException("当前申请对应的审核部门与当前登录用户所在部门不匹配");
        }

        logger.info("较验传入的强检器具是否属于当前申请");
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstrumentSet) {
            Boolean found = false;
            for (MandatoryInstrument realMandatoryInstrument : mandatoryInstrumentApply.getMandatoryInstruments()) {
                if (mandatoryInstrument.getId().equals(realMandatoryInstrument.getId())) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                logger.debug("没找到,remove掉");
                mandatoryInstrumentSet.remove(mandatoryInstrument);
            }
        }

        logger.info("获取持久化数据(前台只传入了ID。如果直接更新，则将间接的删除了除状态、审核日期以外的其它信息)");
        List<MandatoryInstrument> mandatoryInstrumentList =
                (List<MandatoryInstrument>) mandatoryInstrumentService.findAllByMandatoryInstrumentCollection(mandatoryInstrumentSet);

        return mandatoryInstrumentList;
    }


    @Override
    public void handleWhenApplyDoneByApplyId(Long applyId) throws AccessException, IOException {
        logger.info("获取申请实体");
        MandatoryInstrumentApply apply = mandatoryInstrumentApplyRepository.findOne(applyId);
        if (!apply.getDone()) {
            logger.info("查看是否办结");
            throw new AccessException("当前申请未办结");
        }

        logger.info("找出所有的强检器具");
        for (MandatoryInstrument mandatoryInstrument : apply.getMandatoryInstruments()) {
            if (mandatoryInstrument.getStatus() == MandatoryInstrument.STATUS_NEW) {
                logger.info("如果状态为新建，则设置状态为『退回』(此处不进行权限判断，所以未调用内部的设置为退回的方法)");
                mandatoryInstrument.setStatus(MandatoryInstrument.STATUS_BACKED);

                logger.info("设置审核日期");
                mandatoryInstrument.setAuditDate(new Date(new java.util.Date().getTime()));
            }
        }

        logger.info("持久化");
        mandatoryInstrumentRepository.save(apply.getMandatoryInstruments());
    }

    @Override
    public void replyByIdAndReplyOpinions(Long applyId, String replayOpinions) throws ValidationException {
        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyRepository.findOne(applyId);

        logger.info("较验权限");
        this.validateForReply(mandatoryInstrumentApply);

        logger.info("设置审核意见，审核人，并办结");
        mandatoryInstrumentApply.setReplayOpinions(replayOpinions);

        logger.info("办结相关工作");
        workService.updateToDoneByWork(mandatoryInstrumentApply.getCurrentWork().getWork());
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
    }

    protected void validateForReply(MandatoryInstrumentApply mandatoryInstrumentApply) {
        if (mandatoryInstrumentApply == null) {
            throw new ValidationException("未找到相关的申请实体");
        }

        if (mandatoryInstrumentApply.getDone()) {
            throw new ValidationException("当前申请已办结");
        }

        User currentUser = userService.getCurrentLoginUser();
        Work currentWork = workService.findLastWorkByApply(mandatoryInstrumentApply);
        if (!currentUser.getDepartment().getId().equals(currentWork.getAuditDepartment().getId())) {
            throw new ValidationException("当前申请的在办部门非当前登录部门");
        }
    }


    /**
     * 对某个强检器具执行 审核通过
     *
     * @param mandatoryInstrument 强检器具
     * @Author panjie
     */
    private void passMandatoryInstrument(MandatoryInstrument mandatoryInstrument) {
        logger.info("设置审核日期, 改变状态, 下次检定时间(1年)");
        mandatoryInstrument.setAuditDate(new Date(new java.util.Date().getTime()));
        mandatoryInstrument.setStatus(MandatoryInstrument.STATUS_NORMAL);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        mandatoryInstrument.setNextCheckDate(new Date(calendar.getTimeInMillis()));
    }

    /**
     * 对某个强检器具执行 设置退回状态及审核日期，不持久化
     *
     * @param mandatoryInstrument
     * @Author panjie
     */
    private void backMandatoryInstrument(MandatoryInstrument mandatoryInstrument) {
        logger.info("设置审核日期");
        mandatoryInstrument.setAuditDate(new Date(new java.util.Date().getTime()));
        mandatoryInstrument.setStatus(MandatoryInstrument.STATUS_BACKED);
    }

    //    // 对申请的强检器具进行分类,分为四类
//    // 1：区县级技术机构检定的。
//    // 2：市级技术机构检定的。
//    // 3：未通过审核并退回的。
//    // 4：通过审核需要要省级技术机构提出检定申请的。
//    @Override
//    public Map<String, List<MandatoryInstrument>> classificationOfMandatoryInstrument(MandatoryInstrumentApply mandatoryInstrumentApply) {
//        logger.info("根据强制检定备案申请Id获取强制鉴定器具");
//        List<MandatoryInstrument> mandatoryInstruments = mandatoryInstrumentRepository.findAllByMandatoryInstrumentApplyId(mandatoryInstrumentApply.getId());
//
//        logger.info("获取未通过审核并退回的强检器具,并把被退回的强检器具从强制鉴定器具数组中移除");
//        List<MandatoryInstrument> mandatoryInstrumentsOfBacked = new ArrayList<>();
//
//        logger.info("获取区县级技术机构检定的强检器具");
//        List<MandatoryInstrument> checkDepartmentOfMandatoryInstrumentsIsQuxian = new ArrayList<>();
//
//        logger.info("获取市级技术机构检定的强检器具");
//        List<MandatoryInstrument> checkDepartmentOfMandatoryInstrumentsIsShi = new ArrayList<>();
//
//        logger.info("获取省级技术机构检定的强检器具");
//        List<MandatoryInstrument> checkDepartmentOfMandatoryInstrumentsIsSheng = new ArrayList<>();
//
//        mandatoryInstruments.forEach(list -> {
//            if (list.getStatus() == InstrumentEmploymentInfo.STATUS_BACKED) {
//                mandatoryInstrumentsOfBacked.add(list);
//            } else if (null == list.getLastCheckDepartment()) {
//                checkDepartmentOfMandatoryInstrumentsIsSheng.add(list);
//            } else if (list.getLastCheckDepartment().getDistrict().getDistrictType().getPinyin().equals("quxian")) {
//                checkDepartmentOfMandatoryInstrumentsIsQuxian.add(list);
//            } else if (list.getLastCheckDepartment().getDistrict().getDistrictType().getPinyin().equals("shi")) {
//                checkDepartmentOfMandatoryInstrumentsIsShi.add(list);
//            } else {
//                checkDepartmentOfMandatoryInstrumentsIsSheng.add(list);
//            }
//        });
//
//        logger.info("整理分类的强检器具");
//        Map<String, List<MandatoryInstrument>> map = new HashMap<>();
//        map.put(this.BACKED, mandatoryInstrumentsOfBacked);
//        map.put(this.Quxian, checkDepartmentOfMandatoryInstrumentsIsQuxian);
//        map.put(this.Sheng, checkDepartmentOfMandatoryInstrumentsIsSheng);
//        map.put(this.Shi, checkDepartmentOfMandatoryInstrumentsIsShi);
//
//        return map;
//    }
//

//    /**
//     * 将强检申请办结时，发送办结短信
//     * 先接接，再找出字段来发送
//     *
//     * @param mandatoryInstrumentApply
//     */
//    @Override
//    public void setMessageWhenApplyDoneByMandatoryInstrumentApply(MandatoryInstrumentApply mandatoryInstrumentApply) throws IOException {
//        logger.info("获取分类数据");
//        Map<String, List<MandatoryInstrument>> groupMandatoryInstruments = this.classificationOfMandatoryInstrument(mandatoryInstrumentApply);
//
//        logger.info("实始化短信信息");
//        String message = "您共送审了" + mandatoryInstrumentApply.getMandatoryInstruments().size() + "个器具：";
//        if (groupMandatoryInstruments.get(this.Quxian).size() > 0) {
//            logger.info("存在指定为区县技术机构的");
//            message += "其中" + groupMandatoryInstruments.get(this.Quxian).size() + "个指定为" +
//                    groupMandatoryInstruments.get(this.Quxian).get(0).getName() + "检定";
//        }
//
//        if (groupMandatoryInstruments.get(this.Shi).size() > 0) {
//            logger.info("存在指定为市技术机构的");
//            message += "; 其中" + groupMandatoryInstruments.get(this.Shi).size() + "个指定为" +
//                    groupMandatoryInstruments.get(this.Shi).get(0).getName() + "检定;";
//        }
//
//        if (groupMandatoryInstruments.get(this.Sheng).size() > 0) {
//            logger.info("存在审核通过但不具有检定能力的");
//            message += "; 其中" + groupMandatoryInstruments.get(this.Sheng).size() + "个本市不具备检定能力,需要您进入系统导出备案申请表后，至赤峰市工商管理质量技术监督局计量科盖章，申请至自治区管理部门检定";
//        }
//
//        if (groupMandatoryInstruments.get(this.BACKED).size() > 0) {
//            logger.info("存在退回的");
//            message += "; 其中" + groupMandatoryInstruments.get(this.BACKED).size() + "个不符合相关条件并退回";
//        }
//
//
//        message += "。";
//
//        logger.info("获取申请联系方式手机号");
//        String phone = applyFieldRecordService.getValueByApplyAndApplyFieldName(mandatoryInstrumentApply, MandatoryInstrumentApplyService.FIELD_CONTACT_NUMBER);
//        smsService.sendSMSByPhoneNumberAndContent(phone, message);
//        return;
//    }

}
