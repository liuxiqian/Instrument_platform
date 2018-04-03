package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.output.InstrumentCheckedRateView;
import com.mengyunzhi.measurement.repository.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.*;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 器具受检率统计 service测试类
 *
 * @Author 朴世超
 * update zhangxishuo
 * 本测试文件中有三个方法用到了MAX_SIZE,为了加快整体测试速度，将值设置为10
 * 如果以后修改与该测试相关的方法，请使用MAX_SIZE=1000进行测试
 */
public class InstrumentCheckedRateServiceTest extends ServiceTest {

    @Autowired
    private InstrumentCheckedRateRepository instrumentCheckedRateRepository;
    @Autowired
    private InstrumentTypeRepository instrumentTypeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private PurposeRepository purposeRepository;
    @Autowired
    private MandatoryInstrumentRepository mandatoryInstrumentRepository;
    @Autowired
    private InstrumentCheckInfoRepository instrumentCheckInfoRepository;

    @Autowired
    private InstrumentCheckedRateService instrumentCheckedRateService;
    @Autowired
    private MandatoryInstrumentService mandatoryInstrumentService;
    @Autowired
    private ApplianceUserDepartmentService applianceUserDepartmentService;
    @Autowired
    private MandatoryInstrumentCheckInfoService mandatoryInstrumentCheckInfoService;
    @Autowired
    private DistrictTypeService districtTypeService;
    @Autowired
    private DistrictService districtService;

    private static final Logger logger = Logger.getLogger(InstrumentCheckedRateServiceTest.class.getName());

    @Test
    public void clonePreYearForCurrentYear() {
        logger.info("关联实体必须先持久化,后面才能被获取到");
        InstrumentType instrumentType = new InstrumentType();
        instrumentTypeRepository.save(instrumentType);
        Department department = new Department();
        departmentRepository.save(department);
        District province = new District();
        District city = new District();
        District county = new District();
        districtRepository.save(province);
        districtRepository.save(city);
        districtRepository.save(county);
        Purpose purpose = new Purpose();
        purposeRepository.save(purpose);

        logger.info("获取上一年份");
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int preYear = currentYear - 1;  // 上一年份

        logger.info("创建多条数据");
        for (int i = 0; i < 10000; i++) {
            logger.info("创建一个器具受检率统计对象,作为上一年份");
            InstrumentCheckedRate preInstrumentCheckedRate = new InstrumentCheckedRate();

            logger.info("为对象赋值并持久化");
            preInstrumentCheckedRate.setYear((short) preYear);
            preInstrumentCheckedRate.setChecked(false);
            instrumentCheckedRateRepository.save(preInstrumentCheckedRate);

            logger.info("关联实体的赋值");
            preInstrumentCheckedRate.setInstrumentType(instrumentType);
            preInstrumentCheckedRate.setInstrumentUserDepartment(department);
            preInstrumentCheckedRate.setProvinceDistrict(province);
            preInstrumentCheckedRate.setCityDistrict(city);
            preInstrumentCheckedRate.setPurpose(purpose);
            instrumentCheckedRateRepository.save(preInstrumentCheckedRate);
        }

        logger.info("调用service层方法克隆当前年份的的实体对象");
        instrumentCheckedRateService.clonePreYearForCurrentYear();
        logger.info("从数据库中获取刚刚持久化的当前年份的对象");
        List<InstrumentCheckedRate> newInstrumentCheckedRates = instrumentCheckedRateRepository.findAllByYear((short) currentYear);
        logger.info("断言上一年份的记录依然存在");
        assertThat(instrumentCheckedRateRepository.countByYear((short) preYear)).isEqualTo(10000L);
        logger.info("断言新的记录已经 clone");
        assertThat(instrumentCheckedRateRepository.countByYear((short) currentYear)).isEqualTo(10000L);
        logger.info("断言当前年份的对象的属性与上一年份的属性相同");
        for (int i = 0; i < newInstrumentCheckedRates.size(); i++) {
            assertThat(newInstrumentCheckedRates.get(i).getYear()).isEqualTo((short) currentYear);
            assertThat(newInstrumentCheckedRates.get(i).getInstrumentType()).isEqualTo(instrumentType);
            assertThat(newInstrumentCheckedRates.get(i).getInstrumentUserDepartment()).isEqualTo(department);
        }
    }

    /**
     * 生成器具检定率信息单元测试
     * zhangxishuo
     */
    @Test
    public void generateInstrumentCheckedInfoTest() {

        // 起初设置为10000条数据，发现太慢了，考虑到可能影响整体单元测试的速度，修改为1000条
//        int MAX_SIZE = 1000;
        int MAX_SIZE = 10;

        logger.info("获取当前年份");
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        logger.info("获取两千个强检器具");
        List<MandatoryInstrument> checkMandatoryInstrumentList = this.generateMandatoryInstrument(MAX_SIZE);
        this.generateMandatoryInstrument(MAX_SIZE);

        logger.info("为其中一个强检器具添加当年检定信息");
        this.addCheckInfo(currentYear, checkMandatoryInstrumentList);

        logger.info("调用方法生成数据");
        instrumentCheckedRateService.generateInstrumentCheckedInfo();

        logger.info("查询并断言");
        List<InstrumentCheckedRate> Checked = instrumentCheckedRateRepository.findAllByYearAndChecked((short) currentYear, true);
        List<InstrumentCheckedRate> NotChecked = instrumentCheckedRateRepository.findAllByYearAndChecked((short) currentYear, false);
        assertThat(Checked.size()).isEqualTo(MAX_SIZE);
        assertThat(NotChecked.size()).isEqualTo(MAX_SIZE);
    }

    /**
     * 生成强检器具测试数据 重载
     * zhangxishuo
     */
    private List<MandatoryInstrument> generateMandatoryInstrument(int size) {

        Department department = applianceUserDepartmentService.getOneSavedDepartment();

        return this.generateMandatoryInstrument(size, department);
    }

    /**
     * 生成强检器具测试数据 重载
     * zhangxishuo
     */
    private List<MandatoryInstrument> generateMandatoryInstrument(int size, Department department) {

        List<MandatoryInstrument> mandatoryInstrumentList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            logger.info("获取一个强检器具");
            MandatoryInstrument mandatoryInstrument = mandatoryInstrumentService.getOneUnsavedMandatoryInstrument();

            logger.info("为器具设置状态为正常");
            mandatoryInstrument.setStatus(MandatoryInstrument.STATUS_NORMAL);

            logger.info("设置器具用户");
            mandatoryInstrument.setDepartment(department);

            mandatoryInstrumentList.add(mandatoryInstrument);
        }

        mandatoryInstrumentRepository.save(mandatoryInstrumentList);

        return mandatoryInstrumentList;
    }

    /**
     * 为某强检器具，某年添加检定信息
     *
     * @param year                    年份
     * @param mandatoryInstrumentList 强检器具 数组
     * zhangxishuo
     */
    private void addCheckInfo(int year, List<MandatoryInstrument> mandatoryInstrumentList) {

        logger.info("循环，此处是为了提高运行效率");
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstrumentList) {

            logger.info("新建器具检定信息");
            InstrumentCheckInfo instrumentCheckInfo = mandatoryInstrumentCheckInfoService.getOneSavedInstrumentCheckInfo();

            logger.info("获取一个某年的日期实例");
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, Calendar.JANUARY, Calendar.DAY_OF_MONTH);

            logger.info("为该检定信息添加属性");
            instrumentCheckInfo.setCheckDate(new Date(calendar.getTimeInMillis()));

            logger.info("保存");
            instrumentCheckInfoRepository.save(instrumentCheckInfo);

            logger.info("为该强检器具设置最近一次检定信息");
            mandatoryInstrument.setLastInstrumentCheckInfo(instrumentCheckInfo);
        }
    }

    @Test
    public void getAllCheckedRateByYearsAndIdsTest() {
        DistrictType provinceType = districtTypeService.findOneByName("省");
        DistrictType cityType = districtTypeService.findOneByName("市");
        DistrictType countyType = districtTypeService.findOneByName("区\\县");

        logger.info("初始化数据");
//        int MAX_SIZE = 500;
        int MAX_SIZE = 10;

        logger.info("获取区域信息");
        // 河北省
        District province = this.getDistrictByTypeAndParentDistrictAndName(provinceType, null, "河北省");
        // 唐山市
        District city = this.getDistrictByTypeAndParentDistrictAndName(cityType, province, "唐山市");
        // 唐山市下的区县
        District county1 = this.getDistrictByTypeAndParentDistrictAndName(countyType, city, "乐亭县");
        District county2 = this.getDistrictByTypeAndParentDistrictAndName(countyType, city, "迁西县");

        logger.info("获取当前年份");
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        logger.info("获取强检器具信息");

        logger.info("生成500个 河北省-唐山市-乐亭县 器具用户的器具 并检定");
        Department department3 = this.getDepartmentByDistrict(county1);
        List<MandatoryInstrument> mandatoryInstrumentList2 = this.generateMandatoryInstrument(MAX_SIZE, department3);
        this.addCheckInfo(currentYear, mandatoryInstrumentList2);

        logger.info("生成500个 河北省-唐山市-迁西县 器具用户的器具 不检定");
        Department department4 = this.getDepartmentByDistrict(county2);
        this.generateMandatoryInstrument(MAX_SIZE, department4);

        logger.info("生成信息");
        instrumentCheckedRateService.generateInstrumentCheckedInfo();

        logger.info("调用封装的方法断言");
        // 该年 河北省 共 1000 已检 500
        this.checkRateContentTest(currentYear, provinceType, province, MAX_SIZE * 2, MAX_SIZE, true);
        // 该年 唐山市 共 1000 已检 500
        this.checkRateContentTest(currentYear, cityType, city, MAX_SIZE * 2, MAX_SIZE, true);
        // 该年 唐山市-乐亭县 共500 已检 500
        this.checkRateContentTest(currentYear, countyType, county1, MAX_SIZE, MAX_SIZE, true);
        // 该年 唐山市-迁西县 共500 已检 0
        this.checkRateContentTest(currentYear, countyType, county2, MAX_SIZE, 0, true);

    }

    /**
     * 获取检定率信息单元测试
     * zhangxishuo
     */
    @Test
    public void getAllCheckedRateByYearsAndObjectTest() {

        DistrictType provinceType = districtTypeService.findOneByName("省");
        DistrictType cityType = districtTypeService.findOneByName("市");
        DistrictType countyType = districtTypeService.findOneByName("区\\县");

        logger.info("初始化数据");
//        int MAX_SIZE = 500;
        int MAX_SIZE = 10;

        logger.info("获取区域信息");
        // 河北省
        District province = this.getDistrictByTypeAndParentDistrictAndName(provinceType, null, "河北省");
        // 保定市
        District cityBaoding = this.getDistrictByTypeAndParentDistrictAndName(cityType, province, "保定市");
        // 保定市下的区县
        District countyLaishui = this.getDistrictByTypeAndParentDistrictAndName(countyType, cityBaoding, "涞水县");
        District countyFuping = this.getDistrictByTypeAndParentDistrictAndName(countyType, cityBaoding, "阜平县");
        // 唐山市
        District cityTangshan = this.getDistrictByTypeAndParentDistrictAndName(cityType, province, "唐山市");
        // 唐山市下的区县
        District countyLeting = this.getDistrictByTypeAndParentDistrictAndName(countyType, cityTangshan, "乐亭县");
        District countyQianxi = this.getDistrictByTypeAndParentDistrictAndName(countyType, cityTangshan, "迁西县");

        logger.info("获取当前年份");
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        logger.info("获取强检器具信息");

        logger.info("生成500个 河北省-保定市-涞水县 器具用户的器具 并检定");
        Department department1 = this.getDepartmentByDistrict(countyLaishui);
        List<MandatoryInstrument> mandatoryInstrumentList1 = this.generateMandatoryInstrument(MAX_SIZE, department1);
        this.addCheckInfo(currentYear, mandatoryInstrumentList1);

        logger.info("生成500个 河北省-保定市-阜平县 器具用户的器具 不检定");
        Department department2 = this.getDepartmentByDistrict(countyFuping);
        this.generateMandatoryInstrument(MAX_SIZE, department2);

        logger.info("生成500个 河北省-唐山市-乐亭县 器具用户的器具 并检定");
        Department department3 = this.getDepartmentByDistrict(countyLeting);
        List<MandatoryInstrument> mandatoryInstrumentList2 = this.generateMandatoryInstrument(MAX_SIZE, department3);
        this.addCheckInfo(currentYear, mandatoryInstrumentList2);

        logger.info("生成500个 河北省-唐山市-迁西县 器具用户的器具 不检定");
        Department department4 = this.getDepartmentByDistrict(countyQianxi);
        this.generateMandatoryInstrument(MAX_SIZE, department4);

        logger.info("生成信息");
        instrumentCheckedRateService.generateInstrumentCheckedInfo();

        logger.info("调用封装的方法断言");
        // 该年 河北省 共 2000 已检 1000
        this.checkRateContentTest(currentYear, provinceType, province, MAX_SIZE * 4, MAX_SIZE * 2, false);
        // 该年 保定市 共 1000 已检 500
        this.checkRateContentTest(currentYear, cityType, cityBaoding, MAX_SIZE * 2, MAX_SIZE, false);
        // 该年 保定市-涞水县 共500 已检 500
        this.checkRateContentTest(currentYear, countyType, countyLaishui, MAX_SIZE, MAX_SIZE, false);
        // 该年 保定市-阜平县 共500 已检 0
        this.checkRateContentTest(currentYear, countyType, countyFuping, MAX_SIZE, 0, false);
        // 该年 唐山市 共 1000 已检 500
        this.checkRateContentTest(currentYear, cityType, cityTangshan, MAX_SIZE * 2, MAX_SIZE, false);
        // 该年 唐山市-乐亭县 共500 已检 500
        this.checkRateContentTest(currentYear, countyType, countyLeting, MAX_SIZE, MAX_SIZE, false);
        // 该年 唐山市-迁西县 共500 已检 0
        this.checkRateContentTest(currentYear, countyType, countyQianxi, MAX_SIZE, 0, false);
    }

    @Test
    public void getCheckedRateInfoByYearAndIdsTest() {
        DistrictType provinceType = districtTypeService.findOneByName("省");
        DistrictType cityType = districtTypeService.findOneByName("市");
        DistrictType countyType = districtTypeService.findOneByName("区\\县");

        logger.info("初始化数据");
        int MAX_SIZE = 10;

        logger.info("获取区域信息");
        // 河北省
        District province = this.getDistrictByTypeAndParentDistrictAndName(provinceType, null, "河北省");
        // 唐山市
        District cityTangshan = this.getDistrictByTypeAndParentDistrictAndName(cityType, province, "唐山市");
        // 唐山市下的区县
        District countyLeting = this.getDistrictByTypeAndParentDistrictAndName(countyType, cityTangshan, "乐亭县");
        District countyQianxi = this.getDistrictByTypeAndParentDistrictAndName(countyType, cityTangshan, "迁西县");

        logger.info("获取当前年份");
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        logger.info("获取强检器具信息");

        logger.info("生成500个 河北省-唐山市-乐亭县 器具用户的器具 并检定");
        Department department3 = this.getDepartmentByDistrict(countyLeting);
        List<MandatoryInstrument> mandatoryInstrumentList2 = this.generateMandatoryInstrument(MAX_SIZE, department3);
        this.addCheckInfo(currentYear, mandatoryInstrumentList2);

        logger.info("生成500个 河北省-唐山市-迁西县 器具用户的器具 不检定");
        Department department4 = this.getDepartmentByDistrict(countyQianxi);
        this.generateMandatoryInstrument(MAX_SIZE, department4);

        logger.info("生成信息");
        instrumentCheckedRateService.generateInstrumentCheckedInfo();

        List<InstrumentCheckedRateView> instrumentCheckedRateViewList1 = instrumentCheckedRateService.getAllCheckedRate((short) currentYear, province.getId(), null, null);
        assertThat(instrumentCheckedRateViewList1.size()).isEqualTo(districtService.getClassOneSonListByDistrict(province).size());

        List<InstrumentCheckedRateView> instrumentCheckedRateViewList2 = instrumentCheckedRateService.getAllCheckedRate((short) currentYear, cityTangshan.getId(), null, null);
        assertThat(instrumentCheckedRateViewList2.size()).isEqualTo(districtService.getClassOneSonListByDistrict(cityTangshan).size());
    }

    @Test
    public void getCheckedRateInfoByYearAndObjectTest() {

        DistrictType provinceType = districtTypeService.findOneByName("省");
        DistrictType cityType = districtTypeService.findOneByName("市");
        DistrictType countyType = districtTypeService.findOneByName("区\\县");

        logger.info("初始化数据");
        int MAX_SIZE = 10;

        logger.info("获取区域信息");
        // 河北省
        District province = this.getDistrictByTypeAndParentDistrictAndName(provinceType, null, "河北省");
        // 唐山市
        District cityTangshan = this.getDistrictByTypeAndParentDistrictAndName(cityType, province, "唐山市");
        // 唐山市下的区县
        District countyLeting = this.getDistrictByTypeAndParentDistrictAndName(countyType, cityTangshan, "乐亭县");
        District countyQianxi = this.getDistrictByTypeAndParentDistrictAndName(countyType, cityTangshan, "迁西县");

        logger.info("获取当前年份");
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        logger.info("获取强检器具信息");

        logger.info("生成500个 河北省-唐山市-乐亭县 器具用户的器具 并检定");
        Department department3 = this.getDepartmentByDistrict(countyLeting);
        List<MandatoryInstrument> mandatoryInstrumentList2 = this.generateMandatoryInstrument(MAX_SIZE, department3);
        this.addCheckInfo(currentYear, mandatoryInstrumentList2);

        logger.info("生成500个 河北省-唐山市-迁西县 器具用户的器具 不检定");
        Department department4 = this.getDepartmentByDistrict(countyQianxi);
        this.generateMandatoryInstrument(MAX_SIZE, department4);

        logger.info("生成信息");
        instrumentCheckedRateService.generateInstrumentCheckedInfo();

        List<InstrumentCheckedRateView> instrumentCheckedRateViewList1 = instrumentCheckedRateService.getAllCheckedRate((short) currentYear, province, null, null,null, null);
        assertThat(instrumentCheckedRateViewList1.size()).isEqualTo(districtService.getClassOneSonListByDistrict(province).size());

        List<InstrumentCheckedRateView> instrumentCheckedRateViewList2 = instrumentCheckedRateService.getAllCheckedRate((short) currentYear, null, cityTangshan, null, null, null);
        assertThat(instrumentCheckedRateViewList2.size()).isEqualTo(districtService.getClassOneSonListByDistrict(cityTangshan).size());
    }

    /**
     * 测试检定率返回内容
     * 因为有大量重复代码，将其分离出一个单独的方法
     * 注：该方法与getCheckedRateInfoByYearsTest耦合性较高，不建议更改
     * @param currentYear      当前年份
     * @param districtType     区域类型
     * @param district         区域
     * @param instrumentTotal  断言的器具总数
     * @param checkTotal       断言的受检总数
     * @param isById           Service中有两个查询数据的方法，是否通过id查询
     *                         true通过id方法查询，false通过对象方法查询
     * zhangxishuo
     */
    private void checkRateContentTest(int currentYear, DistrictType districtType, District district, int instrumentTotal, int checkTotal, boolean isById) {

        DistrictType provinceType = districtTypeService.findOneByName("省");
        DistrictType cityType = districtTypeService.findOneByName("市");
        DistrictType countyType = districtTypeService.findOneByName("区\\县");

        logger.info("设置时间信息");
        List<Short> years = new ArrayList<>();
        years.add((short) (currentYear - 1));
        years.add((short) currentYear);

        logger.info("根据不同查询条件查询数据");
        List<InstrumentCheckedRateView> instrumentCheckedRateViewList = new ArrayList<>();
        if (isById) {
            instrumentCheckedRateViewList = instrumentCheckedRateService.getAllCheckedRate(years, district.getId(), null, null, null);
        } else if (districtType.equals(provinceType)) {
            instrumentCheckedRateViewList = instrumentCheckedRateService.getAllCheckedRate(years, district, null, null, null, null, null);
        } else if (districtType.equals(cityType)) {
            instrumentCheckedRateViewList = instrumentCheckedRateService.getAllCheckedRate(years, null, district, null, null, null, null);
        } else {
            instrumentCheckedRateViewList = instrumentCheckedRateService.getAllCheckedRate(years, null, null, district, null, null, null);
        }

        logger.info("断言查询的数据条数与年份的条数相等");
        assertThat(instrumentCheckedRateViewList.size()).isEqualTo(years.size());

        for (InstrumentCheckedRateView instrumentCheckedRateView : instrumentCheckedRateViewList) {

            if (instrumentCheckedRateView.getYear() == currentYear) {
                logger.info("如果是该年的数据，则其结果符合刚刚的数据添加结果");
                assertThat(instrumentCheckedRateView.getInstrumentTotal()).isEqualTo(instrumentTotal);
                assertThat(instrumentCheckedRateView.getCheckedTotal()).isEqualTo(checkTotal);
            } else {
                logger.info("否则一律为0，因为未添加往年信息");
                assertThat(instrumentCheckedRateView.getInstrumentTotal()).isZero();
                assertThat(instrumentCheckedRateView.getCheckedTotal()).isZero();
            }
        }
    }

    /**
     * 根据区域获取该区域的部门
     * @param district    区域
     * @return     该区域中的部门
     * zhangxishuo
     */
    private Department getDepartmentByDistrict(District district) {

        logger.info("获取部门并设置区域");
        Department department = applianceUserDepartmentService.getOneSavedDepartment();
        department.setDistrict(district);

        logger.info("保存并返回");
        departmentRepository.save(department);
        return department;
    }

    /**
     * 根据区域类型+父区域+名称获取一个区域
     * @param districtType   区域类型
     * @param parentDistrict 父区域
     * @param name           名称
     * @return               区域
     * zhangxishuo
     */
    private District getDistrictByTypeAndParentDistrictAndName(DistrictType districtType, District parentDistrict, String name) {

        logger.info("新建区域并设置属性");
        District district = new District();
        district.setName(name);
        district.setParentDistrict(parentDistrict);
        district.setDistrictType(districtType);

        logger.info("保存并返回");
        districtRepository.save(district);
        return district;
    }
}