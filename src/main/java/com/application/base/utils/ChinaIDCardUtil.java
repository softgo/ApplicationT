package com.application.base.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * 
 * <p>
 * 类说明:
 * 1.:身份证合法性校验
 * 2.:提取身份证相关信息
 * </p>
 * <p>
 * --15位身份证号码：第7、8位为出生年份(两位数)，第9、10位为出生月份，第11、12位代表出生日期，第15位代表性别，奇数为男，偶数为女。
 * --18位身份证号码：第7、8、9、10位为出生年份(四位数)，第11、第12位为出生月份，第13、14位代表出生日期，第17位代表性别，奇数为男，
 * 偶数为女。
 * </p>
 */
public class ChinaIDCardUtil {
    
    /**
     * test.
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String 
        idCard = "150207199104093141";
        idCard = "130182199109093598";
        idCard = "342427199307076636";
        idCard = "211324199101305312";
        idCard = "65010319930811321x";
        idCard = "612326198809196717";

        boolean flag = ChinaIDCardUtil.isValidated18IDCard(idCard);
        System.out.println(flag);
        System.out.println("年龄："+ChinaIDCardUtil.getAgeByIDCard(idCard));
        System.out.println("生日："+ChinaIDCardUtil.getBirthByIDCard(idCard));
        System.out.println("性别：男M女F："+ChinaIDCardUtil.getGenderByIDCard(idCard));
        System.out.println("省份："+ChinaIDCardUtil.getProvinceByIDCard(idCard));
        System.out.println("出生日期："+ChinaIDCardUtil.getDateByIDCard(idCard));
        System.out.println("出生月："+ChinaIDCardUtil.getMonthByIDCard(idCard));
        System.out.println("出生年："+ChinaIDCardUtil.getYearByIDCard(idCard));
        System.out.println("省份ID:"+ChinaIDCardUtil.getProvinceIDByIDCard(idCard));
        System.err.println("地区："+ChinaIDCardUtil.getCityByIDCard(idCard));
        
    }
    
    /**
     * 省，直辖市编号.
     */
    public static Map<String, String> provinceCodes = new HashMap<String, String>();
    /**
     * 详细地址.
     */
    public static Map<String, String> cityCodes = new HashMap<String, String>();
    /** 台湾身份首字母对应数字 */
    public static Map<String, Integer> twFirstCode = new HashMap<String, Integer>();
    /** 香港身份首字母对应数字 */
    public static Map<String, Integer> hkFirstCode = new HashMap<String, Integer>();
    /**
     * 初始化...
     */
    static {
        provinceCodes.put("11", "北京");
        provinceCodes.put("12", "天津");
        provinceCodes.put("13", "河北");
        provinceCodes.put("14", "山西");
        provinceCodes.put("15", "内蒙古");
        provinceCodes.put("21", "辽宁");
        provinceCodes.put("22", "吉林");
        provinceCodes.put("23", "黑龙江");
        provinceCodes.put("31", "上海");
        provinceCodes.put("32", "江苏");
        provinceCodes.put("33", "浙江");
        provinceCodes.put("34", "安徽");
        provinceCodes.put("35", "福建");
        provinceCodes.put("36", "江西");
        provinceCodes.put("37", "山东");
        provinceCodes.put("41", "河南");
        provinceCodes.put("42", "湖北");
        provinceCodes.put("43", "湖南");
        provinceCodes.put("44", "广东");
        provinceCodes.put("45", "广西");
        provinceCodes.put("46", "海南");
        provinceCodes.put("50", "重庆");
        provinceCodes.put("51", "四川");
        provinceCodes.put("52", "贵州");
        provinceCodes.put("53", "云南");
        provinceCodes.put("54", "西藏");
        provinceCodes.put("61", "陕西");
        provinceCodes.put("62", "甘肃");
        provinceCodes.put("63", "青海");
        provinceCodes.put("64", "宁夏");
        provinceCodes.put("65", "新疆");
        provinceCodes.put("71", "台湾");
        provinceCodes.put("81", "香港");
        provinceCodes.put("82", "澳门");
        provinceCodes.put("91", "国外");
        twFirstCode.put("A", 10);
        twFirstCode.put("B", 11);
        twFirstCode.put("C", 12);
        twFirstCode.put("D", 13);
        twFirstCode.put("E", 14);
        twFirstCode.put("F", 15);
        twFirstCode.put("G", 16);
        twFirstCode.put("H", 17);
        twFirstCode.put("J", 18);
        twFirstCode.put("K", 19);
        twFirstCode.put("L", 20);
        twFirstCode.put("M", 21);
        twFirstCode.put("N", 22);
        twFirstCode.put("P", 23);
        twFirstCode.put("Q", 24);
        twFirstCode.put("R", 25);
        twFirstCode.put("S", 26);
        twFirstCode.put("T", 27);
        twFirstCode.put("U", 28);
        twFirstCode.put("V", 29);
        twFirstCode.put("X", 30);
        twFirstCode.put("Y", 31);
        twFirstCode.put("W", 32);
        twFirstCode.put("Z", 33);
        twFirstCode.put("I", 34);
        twFirstCode.put("O", 35);
        hkFirstCode.put("A", 1);
        hkFirstCode.put("B", 2);
        hkFirstCode.put("C", 3);
        hkFirstCode.put("R", 18);
        hkFirstCode.put("U", 21);
        hkFirstCode.put("Z", 26);
        hkFirstCode.put("X", 24);
        hkFirstCode.put("W", 23);
        hkFirstCode.put("O", 15);
        hkFirstCode.put("N", 14);
        
        //市区.
        cityCodes.put("110000","北京市");
        cityCodes.put("110100","北京市市辖区");
        cityCodes.put("110101","北京市东城区");
        cityCodes.put("110102","北京市西城区");
        cityCodes.put("110103","北京市崇文区");
        cityCodes.put("110104","北京市宣武区");
        cityCodes.put("110105","北京市朝阳区");
        cityCodes.put("110106","北京市丰台区");
        cityCodes.put("110107","北京市石景山区");
        cityCodes.put("110108","北京市海淀区");
        cityCodes.put("110109","北京市门头沟区");
        cityCodes.put("110111","北京市房山区");
        cityCodes.put("110112","北京市通州区");
        cityCodes.put("110113","北京市顺义区");
        cityCodes.put("110200","北京市县");
        cityCodes.put("110221","北京市昌平县");
        cityCodes.put("110224","北京市大兴县");
        cityCodes.put("110226","北京市平谷县");
        cityCodes.put("110227","北京市怀柔县");
        cityCodes.put("110228","北京市密云县");
        cityCodes.put("110229","北京市延庆县");
        cityCodes.put("120000","天津市");
        cityCodes.put("120100","天津市市辖区");
        cityCodes.put("120101","天津市和平区");
        cityCodes.put("120102","天津市河东区");
        cityCodes.put("120103","天津市河西区");
        cityCodes.put("120104","天津市南开区");
        cityCodes.put("120105","天津市河北区");
        cityCodes.put("120106","天津市红桥区");
        cityCodes.put("120107","天津市塘沽区");
        cityCodes.put("120108","天津市汉沽区");
        cityCodes.put("120109","天津市大港区");
        cityCodes.put("120110","天津市东丽区");
        cityCodes.put("120111","天津市西青区");
        cityCodes.put("120112","天津市津南区");
        cityCodes.put("120113","天津市北辰区");
        cityCodes.put("120200","天津市县");
        cityCodes.put("120221","天津市宁河县");
        cityCodes.put("120222","天津市武清县");
        cityCodes.put("120223","天津市静海县");
        cityCodes.put("120224","天津市宝坻县");
        cityCodes.put("120225","天津市蓟县");
        cityCodes.put("130000","河北省");
        cityCodes.put("130100","河北省石家庄市");
        cityCodes.put("130101","河北省石家庄市市辖区");
        cityCodes.put("130102","河北省石家庄市长安区");
        cityCodes.put("130103","河北省石家庄市桥东区");
        cityCodes.put("130104","河北省石家庄市桥西区");
        cityCodes.put("130105","河北省石家庄市新华区");
        cityCodes.put("130106","河北省石家庄市郊区");
        cityCodes.put("130107","河北省石家庄市井陉矿区");
        cityCodes.put("130121","河北省石家庄市井陉县");
        cityCodes.put("130123","河北省石家庄市正定县");
        cityCodes.put("130124","河北省石家庄市栾城县");
        cityCodes.put("130125","河北省石家庄市行唐县");
        cityCodes.put("130126","河北省石家庄市灵寿县");
        cityCodes.put("130127","河北省石家庄市高邑县");
        cityCodes.put("130128","河北省石家庄市深泽县");
        cityCodes.put("130129","河北省石家庄市赞皇县");
        cityCodes.put("130130","河北省石家庄市无极县");
        cityCodes.put("130131","河北省石家庄市平山县");
        cityCodes.put("130132","河北省石家庄市元氏县");
        cityCodes.put("130133","河北省石家庄市赵县");
        cityCodes.put("130181","河北省石家庄市辛集市");
        cityCodes.put("130182","河北省石家庄市藁城市");
        cityCodes.put("130183","河北省石家庄市晋州市");
        cityCodes.put("130184","河北省石家庄市新乐市");
        cityCodes.put("130185","河北省石家庄市鹿泉市");
        cityCodes.put("130200","河北省唐山市");
        cityCodes.put("130201","河北省唐山市市辖区");
        cityCodes.put("130202","河北省唐山市路南区");
        cityCodes.put("130203","河北省唐山市路北区");
        cityCodes.put("130204","河北省唐山市古冶区");
        cityCodes.put("130205","河北省唐山市开平区");
        cityCodes.put("130206","河北省唐山市新区");
        cityCodes.put("130221","河北省唐山市丰润县");
        cityCodes.put("130223","河北省唐山市滦县");
        cityCodes.put("130224","河北省唐山市滦南县");
        cityCodes.put("130225","河北省唐山市乐亭县");
        cityCodes.put("130227","河北省唐山市迁西县");
        cityCodes.put("130229","河北省唐山市玉田县");
        cityCodes.put("130230","河北省唐山市唐海县");
        cityCodes.put("130281","河北省唐山市遵化市");
        cityCodes.put("130282","河北省唐山市丰南市");
        cityCodes.put("130283","河北省唐山市迁安市");
        cityCodes.put("130300","河北省秦皇岛市秦皇岛市");
        cityCodes.put("130301","河北省秦皇岛市市辖区");
        cityCodes.put("130302","河北省秦皇岛市海港区");
        cityCodes.put("130303","河北省秦皇岛市山海关区");
        cityCodes.put("130304","河北省秦皇岛市北戴河区");
        cityCodes.put("130321","河北省秦皇岛市青龙满族自治县");
        cityCodes.put("130322","河北省秦皇岛市昌黎县");
        cityCodes.put("130323","河北省秦皇岛市抚宁县");
        cityCodes.put("130324","河北省秦皇岛市卢龙县");
        cityCodes.put("130400","河北省邯郸市邯郸市");
        cityCodes.put("130401","河北省邯郸市市辖区");
        cityCodes.put("130402","河北省邯郸市邯山区");
        cityCodes.put("130403","河北省邯郸市丛台区");
        cityCodes.put("130404","河北省邯郸市复兴区");
        cityCodes.put("130406","河北省邯郸市峰峰矿区");
        cityCodes.put("130421","河北省邯郸市邯郸县");
        cityCodes.put("130423","河北省邯郸市临漳县");
        cityCodes.put("130424","河北省邯郸市成安县");
        cityCodes.put("130425","河北省邯郸市大名县");
        cityCodes.put("130426","河北省邯郸市涉县");
        cityCodes.put("130427","河北省邯郸市磁县");
        cityCodes.put("130428","河北省邯郸市肥乡县");
        cityCodes.put("130429","河北省邯郸市永年县");
        cityCodes.put("130430","河北省邯郸市邱县");
        cityCodes.put("130431","河北省邯郸市鸡泽县");
        cityCodes.put("130432","河北省邯郸市广平县");
        cityCodes.put("130433","河北省邯郸市馆陶县");
        cityCodes.put("130434","河北省邯郸市魏县");
        cityCodes.put("130435","河北省邯郸市曲周县");
        cityCodes.put("130481","河北省邯郸市武安市");
        cityCodes.put("130500","河北省邢台市");
        cityCodes.put("130501","河北省邢台市市辖区");
        cityCodes.put("130502","河北省邢台市桥东区");
        cityCodes.put("130503","河北省邢台市桥西区");
        cityCodes.put("130521","河北省邢台市邢台县");
        cityCodes.put("130522","河北省邢台市临城县");
        cityCodes.put("130523","河北省邢台市内丘县");
        cityCodes.put("130524","河北省邢台市柏乡县");
        cityCodes.put("130525","河北省邢台市隆尧县");
        cityCodes.put("130526","河北省邢台市任县");
        cityCodes.put("130527","河北省邢台市南和县");
        cityCodes.put("130528","河北省邢台市宁晋县");
        cityCodes.put("130529","河北省邢台市巨鹿县");
        cityCodes.put("130530","河北省邢台市新河县");
        cityCodes.put("130531","河北省邢台市广宗县");
        cityCodes.put("130532","河北省邢台市平乡县");
        cityCodes.put("130533","河北省邢台市威县");
        cityCodes.put("130534","河北省邢台市清河县");
        cityCodes.put("130535","河北省邢台市临西县");
        cityCodes.put("130581","河北省邢台市南宫市");
        cityCodes.put("130582","河北省邢台市沙河市");
        cityCodes.put("130600","河北省保定市");
        cityCodes.put("130601","河北省保定市市辖区");
        cityCodes.put("130602","河北省保定市新市区");
        cityCodes.put("130603","河北省保定市北市区");
        cityCodes.put("130604","河北省保定市南市区");
        cityCodes.put("130621","河北省保定市满城县");
        cityCodes.put("130622","河北省保定市清苑县");
        cityCodes.put("130623","河北省保定市涞水县");
        cityCodes.put("130624","河北省保定市阜平县");
        cityCodes.put("130625","河北省保定市徐水县");
        cityCodes.put("130626","河北省保定市定兴县");
        cityCodes.put("130627","河北省保定市唐县");
        cityCodes.put("130628","河北省保定市高阳县");
        cityCodes.put("130629","河北省保定市容城县");
        cityCodes.put("130630","河北省保定市涞源县");
        cityCodes.put("130631","河北省保定市望都县");
        cityCodes.put("130632","河北省保定市安新县");
        cityCodes.put("130633","河北省保定市易县");
        cityCodes.put("130634","河北省保定市曲阳县");
        cityCodes.put("130635","河北省保定市蠡县");
        cityCodes.put("130636","河北省保定市顺平县");
        cityCodes.put("130637","河北省保定市博野县");
        cityCodes.put("130638","河北省保定市雄县");
        cityCodes.put("130681","河北省保定市涿州市");
        cityCodes.put("130682","河北省保定市定州市");
        cityCodes.put("130683","河北省保定市安国市");
        cityCodes.put("130684","河北省保定市高碑店市");
        cityCodes.put("130700","河北省张家口市");
        cityCodes.put("130701","河北省张家口市市辖区");
        cityCodes.put("130702","河北省张家口市桥东区");
        cityCodes.put("130703","河北省张家口市桥西区");
        cityCodes.put("130705","河北省张家口市宣化区");
        cityCodes.put("130706","河北省张家口市下花园区");
        cityCodes.put("130721","河北省张家口市宣化县");
        cityCodes.put("130722","河北省张家口市张北县");
        cityCodes.put("130723","河北省张家口市康保县");
        cityCodes.put("130724","河北省张家口市沽源县");
        cityCodes.put("130725","河北省张家口市尚义县");
        cityCodes.put("130726","河北省张家口市蔚县");
        cityCodes.put("130727","河北省张家口市阳原县");
        cityCodes.put("130728","河北省张家口市怀安县");
        cityCodes.put("130729","河北省张家口市万全县");
        cityCodes.put("130730","河北省张家口市怀来县");
        cityCodes.put("130731","河北省张家口市涿鹿县");
        cityCodes.put("130732","河北省张家口市赤城县");
        cityCodes.put("130733","河北省张家口市崇礼县");
        cityCodes.put("130800","河北省承德市");
        cityCodes.put("130801","河北省承德市市辖区");
        cityCodes.put("130802","河北省承德市双桥区");
        cityCodes.put("130803","河北省承德市双滦区");
        cityCodes.put("130804","河北省承德市鹰手营子矿区");
        cityCodes.put("130821","河北省承德市承德县");
        cityCodes.put("130822","河北省承德市兴隆县");
        cityCodes.put("130823","河北省承德市平泉县");
        cityCodes.put("130824","河北省承德市滦平县");
        cityCodes.put("130825","河北省承德市隆化县");
        cityCodes.put("130826","河北省承德市丰宁满族自治县");
        cityCodes.put("130827","河北省承德市宽城满族自治县");
        cityCodes.put("130828","河北省承德市围场满族蒙古族自治县");
        cityCodes.put("130900","河北省沧州市");
        cityCodes.put("130901","河北省沧州市市辖区");
        cityCodes.put("130902","河北省沧州市新华区");
        cityCodes.put("130903","河北省沧州市运河区");
        cityCodes.put("130921","河北省沧州市沧县");
        cityCodes.put("130922","河北省沧州市青县");
        cityCodes.put("130923","河北省沧州市东光县");
        cityCodes.put("130924","河北省沧州市海兴县");
        cityCodes.put("130925","河北省沧州市盐山县");
        cityCodes.put("130926","河北省沧州市肃宁县");
        cityCodes.put("130927","河北省沧州市南皮县");
        cityCodes.put("130928","河北省沧州市吴桥县");
        cityCodes.put("130929","河北省沧州市献县");
        cityCodes.put("130930","河北省沧州市孟村回族自治县");
        cityCodes.put("130981","河北省沧州市泊头市");
        cityCodes.put("130982","河北省沧州市任丘市");
        cityCodes.put("130983","河北省沧州市黄骅市");
        cityCodes.put("130984","河北省沧州市河间市");
        cityCodes.put("131000","河北省廊坊市");
        cityCodes.put("131001","河北省廊坊市市辖区");
        cityCodes.put("131002","河北省廊坊市安次区");
        cityCodes.put("131022","河北省廊坊市固安县");
        cityCodes.put("131023","河北省廊坊市永清县");
        cityCodes.put("131024","河北省廊坊市香河县");
        cityCodes.put("131025","河北省廊坊市大城县");
        cityCodes.put("131026","河北省廊坊市文安县");
        cityCodes.put("131028","河北省廊坊市大厂回族自治县");
        cityCodes.put("131081","河北省廊坊市霸州市");
        cityCodes.put("131082","河北省廊坊市三河市");
        cityCodes.put("131100","河北省衡水市");
        cityCodes.put("131101","河北省衡水市市辖区");
        cityCodes.put("131102","河北省衡水市桃城区");
        cityCodes.put("131121","河北省衡水市枣强县");
        cityCodes.put("131122","河北省衡水市武邑县");
        cityCodes.put("131123","河北省衡水市武强县");
        cityCodes.put("131124","河北省衡水市饶阳县");
        cityCodes.put("131125","河北省衡水市安平县");
        cityCodes.put("131126","河北省衡水市故城县");
        cityCodes.put("131127","河北省衡水市景县");
        cityCodes.put("131128","河北省衡水市阜城县");
        cityCodes.put("131181","河北省衡水市冀州市");
        cityCodes.put("131182","河北省衡水市深州市");
        cityCodes.put("140000","山西省");
        cityCodes.put("140100","山西省太原市");
        cityCodes.put("140101","山西省太原市市辖区");
        cityCodes.put("140105","山西省太原市小店区");
        cityCodes.put("140106","山西省太原市迎泽区");
        cityCodes.put("140107","山西省太原市杏花岭区");
        cityCodes.put("140108","山西省太原市尖草坪区");
        cityCodes.put("140109","山西省太原市万柏林区");
        cityCodes.put("140110","山西省太原市晋源区");
        cityCodes.put("140121","山西省太原市清徐县");
        cityCodes.put("140122","山西省太原市阳曲县");
        cityCodes.put("140123","山西省太原市娄烦县");
        cityCodes.put("140181","山西省太原市古交市");
        cityCodes.put("140200","山西省大同市");
        cityCodes.put("140201","山西省大同市市辖区");
        cityCodes.put("140202","山西省大同市城区");
        cityCodes.put("140203","山西省大同市矿区");
        cityCodes.put("140211","山西省大同市南郊区");
        cityCodes.put("140212","山西省大同市新荣区");
        cityCodes.put("140221","山西省大同市阳高县");
        cityCodes.put("140222","山西省大同市天镇县");
        cityCodes.put("140223","山西省大同市广灵县");
        cityCodes.put("140224","山西省大同市灵丘县");
        cityCodes.put("140225","山西省大同市浑源县");
        cityCodes.put("140226","山西省大同市左云县");
        cityCodes.put("140227","山西省大同市大同县");
        cityCodes.put("140300","山西省阳泉市");
        cityCodes.put("140301","山西省阳泉市市辖区");
        cityCodes.put("140302","山西省阳泉市城区");
        cityCodes.put("140303","山西省阳泉市矿区");
        cityCodes.put("140311","山西省阳泉市郊区");
        cityCodes.put("140321","山西省阳泉市平定县");
        cityCodes.put("140322","山西省阳泉市盂县");
        cityCodes.put("140400","山西省长治市");
        cityCodes.put("140401","山西省长治市市辖区");
        cityCodes.put("140402","山西省长治市城区");
        cityCodes.put("140411","山西省长治市郊区");
        cityCodes.put("140421","山西省长治市长治县");
        cityCodes.put("140423","山西省长治市襄垣县");
        cityCodes.put("140424","山西省长治市屯留县");
        cityCodes.put("140425","山西省长治市平顺县");
        cityCodes.put("140426","山西省长治市黎城县");
        cityCodes.put("140427","山西省长治市壶关县");
        cityCodes.put("140428","山西省长治市长子县");
        cityCodes.put("140429","山西省长治市武乡县");
        cityCodes.put("140430","山西省长治市沁县");
        cityCodes.put("140431","山西省长治市沁源县");
        cityCodes.put("140481","山西省长治市潞城市");
        cityCodes.put("140500","山西省晋城市");
        cityCodes.put("140501","山西省晋城市市辖区");
        cityCodes.put("140502","山西省晋城市城区");
        cityCodes.put("140521","山西省晋城市沁水县");
        cityCodes.put("140522","山西省晋城市阳城县");
        cityCodes.put("140524","山西省晋城市陵川县");
        cityCodes.put("140525","山西省晋城市泽州县");
        cityCodes.put("140581","山西省晋城市高平市");
        cityCodes.put("140600","山西省晋城市朔州市");
        cityCodes.put("140601","山西省晋城市市辖区");
        cityCodes.put("140602","山西省晋城市朔城区");
        cityCodes.put("140603","山西省晋城市平鲁区");
        cityCodes.put("140621","山西省晋城市山阴县");
        cityCodes.put("140622","山西省晋城市应县");
        cityCodes.put("140623","山西省晋城市右玉县");
        cityCodes.put("140624","山西省晋城市怀仁县");
        cityCodes.put("142200","山西省忻州地区");
        cityCodes.put("142201","山西省忻州地区忻州市");
        cityCodes.put("142202","山西省忻州地区原平市");
        cityCodes.put("142222","山西省忻州地区定襄县");
        cityCodes.put("142223","山西省忻州地区五台县");
        cityCodes.put("142225","山西省忻州地区代县");
        cityCodes.put("142226","山西省忻州地区繁峙县");
        cityCodes.put("142227","山西省忻州地区宁武县");
        cityCodes.put("142228","山西省忻州地区静乐县");
        cityCodes.put("142229","山西省忻州地区神池县");
        cityCodes.put("142230","山西省忻州地区五寨县");
        cityCodes.put("142231","山西省忻州地区岢岚县");
        cityCodes.put("142232","山西省忻州地区河曲县");
        cityCodes.put("142233","山西省忻州地区保德县");
        cityCodes.put("142234","山西省忻州地区偏关县");
        cityCodes.put("142300","山西省忻州地区吕梁地区");
        cityCodes.put("142301","山西省忻州地区孝义市");
        cityCodes.put("142302","山西省忻州地区离石市");
        cityCodes.put("142303","山西省忻州地区汾阳市");
        cityCodes.put("142322","山西省忻州地区文水县");
        cityCodes.put("142323","山西省忻州地区交城县");
        cityCodes.put("142325","山西省忻州地区兴县");
        cityCodes.put("142326","山西省忻州地区临县");
        cityCodes.put("142327","山西省忻州地区柳林县");
        cityCodes.put("142328","山西省忻州地区石楼县");
        cityCodes.put("142329","山西省忻州地区岚县");
        cityCodes.put("142330","山西省忻州地区方山县");
        cityCodes.put("142332","山西省忻州地区中阳县");
        cityCodes.put("142333","山西省忻州地区交口县");
        cityCodes.put("142400","山西省晋中地区");
        cityCodes.put("142401","山西省晋中地区榆次市");
        cityCodes.put("142402","山西省晋中地区介休市");
        cityCodes.put("142421","山西省晋中地区榆社县");
        cityCodes.put("142422","山西省晋中地区左权县");
        cityCodes.put("142423","山西省晋中地区和顺县");
        cityCodes.put("142424","山西省晋中地区昔阳县");
        cityCodes.put("142427","山西省晋中地区寿阳县");
        cityCodes.put("142429","山西省晋中地区太谷县");
        cityCodes.put("142430","山西省晋中地区祁县");
        cityCodes.put("142431","山西省晋中地区平遥县");
        cityCodes.put("142433","山西省晋中地区灵石县");
        cityCodes.put("142600","山西省临汾地区");
        cityCodes.put("142601","山西省临汾地区临汾市");
        cityCodes.put("142602","山西省临汾地区侯马市");
        cityCodes.put("142603","山西省临汾地区霍州市");
        cityCodes.put("142621","山西省临汾地区曲沃县");
        cityCodes.put("142622","山西省临汾地区翼城县");
        cityCodes.put("142623","山西省临汾地区襄汾县");
        cityCodes.put("142625","山西省临汾地区洪洞县");
        cityCodes.put("142627","山西省临汾地区古县");
        cityCodes.put("142628","山西省临汾地区安泽县");
        cityCodes.put("142629","山西省临汾地区浮山县");
        cityCodes.put("142630","山西省临汾地区吉县");
        cityCodes.put("142631","山西省临汾地区乡宁县");
        cityCodes.put("142632","山西省临汾地区蒲县");
        cityCodes.put("142633","山西省临汾地区大宁县");
        cityCodes.put("142634","山西省临汾地区永和县");
        cityCodes.put("142635","山西省临汾地区隰县");
        cityCodes.put("142636","山西省临汾地区汾西县");
        cityCodes.put("142700","山西省运城地区");
        cityCodes.put("142701","山西省运城地区运城市");
        cityCodes.put("142702","山西省运城地区永济市");
        cityCodes.put("142703","山西省运城地区河津市");
        cityCodes.put("142723","山西省运城地区芮城县");
        cityCodes.put("142724","山西省运城地区临猗县");
        cityCodes.put("142725","山西省运城地区万荣县");
        cityCodes.put("142726","山西省运城地区新绛县");
        cityCodes.put("142727","山西省运城地区稷山县");
        cityCodes.put("142729","山西省运城地区闻喜县");
        cityCodes.put("142730","山西省运城地区夏县");
        cityCodes.put("142731","山西省运城地区绛县");
        cityCodes.put("142732","山西省运城地区平陆县");
        cityCodes.put("142733","山西省运城地区垣曲县");
        cityCodes.put("150000","内蒙古自治区");
        cityCodes.put("150100","内蒙古自治区呼和浩特市");
        cityCodes.put("150101","内蒙古自治区呼和浩特市市辖区");
        cityCodes.put("150102","内蒙古自治区呼和浩特市新城区");
        cityCodes.put("150103","内蒙古自治区呼和浩特市回民区");
        cityCodes.put("150104","内蒙古自治区呼和浩特市玉泉区");
        cityCodes.put("150105","内蒙古自治区呼和浩特市郊区");
        cityCodes.put("150121","内蒙古自治区呼和浩特市土默特左旗");
        cityCodes.put("150122","内蒙古自治区呼和浩特市托克托县");
        cityCodes.put("150123","内蒙古自治区呼和浩特市和林格尔县");
        cityCodes.put("150124","内蒙古自治区呼和浩特市清水河县");
        cityCodes.put("150125","内蒙古自治区呼和浩特市武川县");
        cityCodes.put("150200","内蒙古自治区包头市");
        cityCodes.put("150201","内蒙古自治区包头市市辖区");
        cityCodes.put("150202","内蒙古自治区包头市东河区");
        cityCodes.put("150203","内蒙古自治区包头市昆都伦区");
        cityCodes.put("150204","内蒙古自治区包头市青山区");
        cityCodes.put("150205","内蒙古自治区包头市石拐矿区");
        cityCodes.put("150206","内蒙古自治区包头市白云矿区");
        cityCodes.put("150207","内蒙古自治区包头市郊区");
        cityCodes.put("150221","内蒙古自治区包头市土默特右旗");
        cityCodes.put("150222","内蒙古自治区包头市固阳县");
        cityCodes.put("150223","内蒙古自治区包头市达尔罕茂明安联合旗");
        cityCodes.put("150300","内蒙古自治区乌海市");
        cityCodes.put("150301","内蒙古自治区乌海市市辖区");
        cityCodes.put("150302","内蒙古自治区乌海市海勃湾区");
        cityCodes.put("150303","内蒙古自治区乌海市海南区");
        cityCodes.put("150304","内蒙古自治区乌海市乌达区");
        cityCodes.put("150400","内蒙古自治区赤峰市");
        cityCodes.put("150401","内蒙古自治区赤峰市市辖区");
        cityCodes.put("150402","内蒙古自治区赤峰市红山区");
        cityCodes.put("150403","内蒙古自治区赤峰市元宝山区");
        cityCodes.put("150404","内蒙古自治区赤峰市松山区");
        cityCodes.put("150421","内蒙古自治区赤峰市阿鲁科尔沁旗");
        cityCodes.put("150422","内蒙古自治区赤峰市巴林左旗");
        cityCodes.put("150423","内蒙古自治区赤峰市巴林右旗");
        cityCodes.put("150424","内蒙古自治区赤峰市林西县");
        cityCodes.put("150425","内蒙古自治区赤峰市克什克腾旗");
        cityCodes.put("150426","内蒙古自治区赤峰市翁牛特旗");
        cityCodes.put("150428","内蒙古自治区赤峰市喀喇沁旗");
        cityCodes.put("150429","内蒙古自治区赤峰市宁城县");
        cityCodes.put("150430","内蒙古自治区赤峰市敖汉旗");
        cityCodes.put("152100","内蒙古自治区呼伦贝尔盟");
        cityCodes.put("152101","内蒙古自治区呼伦贝尔盟海拉尔市");
        cityCodes.put("152102","内蒙古自治区呼伦贝尔盟满洲里市");
        cityCodes.put("152103","内蒙古自治区呼伦贝尔盟扎兰屯市");
        cityCodes.put("152104","内蒙古自治区呼伦贝尔盟牙克石市");
        cityCodes.put("152105","内蒙古自治区呼伦贝尔盟根河市");
        cityCodes.put("152106","内蒙古自治区呼伦贝尔盟额尔古纳市");
        cityCodes.put("152122","内蒙古自治区呼伦贝尔盟阿荣旗");
        cityCodes.put("152123","内蒙古自治区呼伦贝尔盟莫力达瓦达斡尔族自治旗");
        cityCodes.put("152127","内蒙古自治区呼伦贝尔盟鄂伦春自治旗");
        cityCodes.put("152128","内蒙古自治区呼伦贝尔盟鄂温克族自治旗");
        cityCodes.put("152129","内蒙古自治区呼伦贝尔盟新巴尔虎右旗");
        cityCodes.put("152130","内蒙古自治区呼伦贝尔盟新巴尔虎左旗");
        cityCodes.put("152131","内蒙古自治区呼伦贝尔盟陈巴尔虎旗");
        cityCodes.put("152200","内蒙古自治区兴安盟");
        cityCodes.put("152201","内蒙古自治区兴安盟乌兰浩特市");
        cityCodes.put("152202","内蒙古自治区兴安盟阿尔山市");
        cityCodes.put("152221","内蒙古自治区兴安盟科尔沁右翼前旗");
        cityCodes.put("152222","内蒙古自治区兴安盟科尔沁右翼中旗");
        cityCodes.put("152223","内蒙古自治区兴安盟扎赉特旗");
        cityCodes.put("152224","内蒙古自治区兴安盟突泉县");
        cityCodes.put("152300","内蒙古自治区哲里木盟");
        cityCodes.put("152301","内蒙古自治区哲里木盟通辽市");
        cityCodes.put("152302","内蒙古自治区哲里木盟霍林郭勒市");
        cityCodes.put("152322","内蒙古自治区哲里木盟科尔沁左翼中旗");
        cityCodes.put("152323","内蒙古自治区哲里木盟科尔沁左翼后旗");
        cityCodes.put("152324","内蒙古自治区哲里木盟开鲁县");
        cityCodes.put("152325","内蒙古自治区哲里木盟库伦旗");
        cityCodes.put("152326","内蒙古自治区哲里木盟奈曼旗");
        cityCodes.put("152327","内蒙古自治区哲里木盟扎鲁特旗");
        cityCodes.put("152500","内蒙古自治区锡林郭勒盟");
        cityCodes.put("152501","内蒙古自治区锡林郭勒盟二连浩特市");
        cityCodes.put("152502","内蒙古自治区锡林郭勒盟锡林浩特市");
        cityCodes.put("152522","内蒙古自治区锡林郭勒盟阿巴嘎旗");
        cityCodes.put("152523","内蒙古自治区锡林郭勒盟苏尼特左旗");
        cityCodes.put("152524","内蒙古自治区锡林郭勒盟苏尼特右旗");
        cityCodes.put("152525","内蒙古自治区锡林郭勒盟东乌珠穆沁旗");
        cityCodes.put("152526","内蒙古自治区锡林郭勒盟西乌珠穆沁旗");
        cityCodes.put("152527","内蒙古自治区锡林郭勒盟太仆寺旗");
        cityCodes.put("152528","内蒙古自治区锡林郭勒盟镶黄旗");
        cityCodes.put("152529","内蒙古自治区锡林郭勒盟正镶白旗");
        cityCodes.put("152530","内蒙古自治区锡林郭勒盟正蓝旗");
        cityCodes.put("152531","内蒙古自治区锡林郭勒盟多伦县");
        cityCodes.put("152600","内蒙古自治区乌兰察布盟");
        cityCodes.put("152601","内蒙古自治区乌兰察布盟集宁市");
        cityCodes.put("152602","内蒙古自治区乌兰察布盟丰镇市");
        cityCodes.put("152624","内蒙古自治区乌兰察布盟卓资县");
        cityCodes.put("152625","内蒙古自治区乌兰察布盟化德县");
        cityCodes.put("152626","内蒙古自治区乌兰察布盟商都县");
        cityCodes.put("152627","内蒙古自治区乌兰察布盟兴和县");
        cityCodes.put("152629","内蒙古自治区乌兰察布盟凉城县");
        cityCodes.put("152630","内蒙古自治区乌兰察布盟察哈尔右翼前旗");
        cityCodes.put("152631","内蒙古自治区乌兰察布盟察哈尔右翼中旗");
        cityCodes.put("152632","内蒙古自治区乌兰察布盟察哈尔右翼后旗");
        cityCodes.put("152634","内蒙古自治区乌兰察布盟四子王旗");
        cityCodes.put("152700","内蒙古自治区伊克昭盟");
        cityCodes.put("152701","内蒙古自治区伊克昭盟东胜市");
        cityCodes.put("152722","内蒙古自治区伊克昭盟达拉特旗");
        cityCodes.put("152723","内蒙古自治区伊克昭盟准格尔旗");
        cityCodes.put("152724","内蒙古自治区伊克昭盟鄂托克前旗");
        cityCodes.put("152725","内蒙古自治区伊克昭盟鄂托克旗");
        cityCodes.put("152726","内蒙古自治区伊克昭盟杭锦旗");
        cityCodes.put("152727","内蒙古自治区伊克昭盟乌审旗");
        cityCodes.put("152728","内蒙古自治区伊克昭盟伊金霍洛旗");
        cityCodes.put("152800","内蒙古自治区巴彦淖尔盟");
        cityCodes.put("152801","内蒙古自治区巴彦淖尔盟临河市");
        cityCodes.put("152822","内蒙古自治区巴彦淖尔盟五原县");
        cityCodes.put("152823","内蒙古自治区巴彦淖尔盟磴口县");
        cityCodes.put("152824","内蒙古自治区巴彦淖尔盟乌拉特前旗");
        cityCodes.put("152825","内蒙古自治区巴彦淖尔盟乌拉特中旗");
        cityCodes.put("152826","内蒙古自治区巴彦淖尔盟乌拉特后旗");
        cityCodes.put("152827","内蒙古自治区巴彦淖尔盟杭锦后旗");
        cityCodes.put("152900","内蒙古自治区阿拉善盟");
        cityCodes.put("152921","内蒙古自治区阿拉善盟阿拉善左旗");
        cityCodes.put("152922","内蒙古自治区阿拉善盟阿拉善右旗");
        cityCodes.put("152923","内蒙古自治区阿拉善盟额济纳旗");
        cityCodes.put("210000","辽宁省");
        cityCodes.put("210100","辽宁省沈阳市");
        cityCodes.put("210101","辽宁省沈阳市市辖区");
        cityCodes.put("210102","辽宁省沈阳市和平区");
        cityCodes.put("210103","辽宁省沈阳市沈河区");
        cityCodes.put("210104","辽宁省沈阳市大东区");
        cityCodes.put("210105","辽宁省沈阳市皇姑区");
        cityCodes.put("210106","辽宁省沈阳市铁西区");
        cityCodes.put("210111","辽宁省沈阳市苏家屯区");
        cityCodes.put("210112","辽宁省沈阳市东陵区");
        cityCodes.put("210113","辽宁省沈阳市新城子区");
        cityCodes.put("210114","辽宁省沈阳市于洪区");
        cityCodes.put("210122","辽宁省沈阳市辽中县");
        cityCodes.put("210123","辽宁省沈阳市康平县");
        cityCodes.put("210124","辽宁省沈阳市法库县");
        cityCodes.put("210181","辽宁省沈阳市新民市");
        cityCodes.put("210200","辽宁省大连市");
        cityCodes.put("210201","辽宁省大连市市辖区");
        cityCodes.put("210202","辽宁省大连市中山区");
        cityCodes.put("210203","辽宁省大连市西岗区");
        cityCodes.put("210204","辽宁省大连市沙河口区");
        cityCodes.put("210211","辽宁省大连市甘井子区");
        cityCodes.put("210212","辽宁省大连市旅顺口区");
        cityCodes.put("210213","辽宁省大连市金州区");
        cityCodes.put("210224","辽宁省大连市长海县");
        cityCodes.put("210281","辽宁省大连市瓦房店市");
        cityCodes.put("210282","辽宁省大连市普兰店市");
        cityCodes.put("210283","辽宁省大连市庄河市");
        cityCodes.put("210300","辽宁省鞍山市");
        cityCodes.put("210301","辽宁省鞍山市市辖区");
        cityCodes.put("210302","辽宁省鞍山市铁东区");
        cityCodes.put("210303","辽宁省鞍山市铁西区");
        cityCodes.put("210304","辽宁省鞍山市立山区");
        cityCodes.put("210311","辽宁省鞍山市千山区");
        cityCodes.put("210321","辽宁省鞍山市台安县");
        cityCodes.put("210323","辽宁省鞍山市岫岩满族自治县");
        cityCodes.put("210381","辽宁省鞍山市海城市");
        cityCodes.put("210400","辽宁省抚顺市");
        cityCodes.put("210401","辽宁省抚顺市市辖区");
        cityCodes.put("210402","辽宁省抚顺市新抚区");
        cityCodes.put("210403","辽宁省抚顺市露天区");
        cityCodes.put("210404","辽宁省抚顺市望花区");
        cityCodes.put("210411","辽宁省抚顺市顺城区");
        cityCodes.put("210421","辽宁省抚顺市抚顺县");
        cityCodes.put("210422","辽宁省抚顺市新宾满族自治县");
        cityCodes.put("210423","辽宁省抚顺市清原满族自治县");
        cityCodes.put("210500","辽宁省本溪市");
        cityCodes.put("210501","辽宁省本溪市市辖区");
        cityCodes.put("210502","辽宁省本溪市平山区");
        cityCodes.put("210503","辽宁省本溪市溪湖区");
        cityCodes.put("210504","辽宁省本溪市明山区");
        cityCodes.put("210505","辽宁省本溪市南芬区");
        cityCodes.put("210521","辽宁省本溪市本溪满族自治县");
        cityCodes.put("210522","辽宁省本溪市桓仁满族自治县");
        cityCodes.put("210600","辽宁省丹东市");
        cityCodes.put("210601","辽宁省丹东市市辖区");
        cityCodes.put("210602","辽宁省丹东市元宝区");
        cityCodes.put("210603","辽宁省丹东市振兴区");
        cityCodes.put("210604","辽宁省丹东市振安区");
        cityCodes.put("210624","辽宁省丹东市宽甸满族自治县");
        cityCodes.put("210681","辽宁省丹东市东港市");
        cityCodes.put("210682","辽宁省丹东市凤城市");
        cityCodes.put("210700","辽宁省锦州市");
        cityCodes.put("210701","辽宁省锦州市市辖区");
        cityCodes.put("210702","辽宁省锦州市古塔区");
        cityCodes.put("210703","辽宁省锦州市凌河区");
        cityCodes.put("210711","辽宁省锦州市太和区");
        cityCodes.put("210726","辽宁省锦州市黑山县");
        cityCodes.put("210727","辽宁省锦州市义县");
        cityCodes.put("210781","辽宁省锦州市凌海市");
        cityCodes.put("210782","辽宁省锦州市北宁市");
        cityCodes.put("210800","辽宁省营口市");
        cityCodes.put("210801","辽宁省营口市市辖区");
        cityCodes.put("210802","辽宁省营口市站前区");
        cityCodes.put("210803","辽宁省营口市西市区");
        cityCodes.put("210804","辽宁省营口市鲅鱼圈区");
        cityCodes.put("210811","辽宁省营口市老边区");
        cityCodes.put("210881","辽宁省营口市盖州市");
        cityCodes.put("210882","辽宁省营口市大石桥市");
        cityCodes.put("210900","辽宁省阜新市");
        cityCodes.put("210901","辽宁省阜新市市辖区");
        cityCodes.put("210902","辽宁省阜新市海州区");
        cityCodes.put("210903","辽宁省阜新市新邱区");
        cityCodes.put("210904","辽宁省阜新市太平区");
        cityCodes.put("210905","辽宁省阜新市清河门区");
        cityCodes.put("210911","辽宁省阜新市细河区");
        cityCodes.put("210921","辽宁省阜新市阜新蒙古族自治县");
        cityCodes.put("210922","辽宁省阜新市彰武县");
        cityCodes.put("211000","辽宁省辽阳市");
        cityCodes.put("211001","辽宁省辽阳市市辖区");
        cityCodes.put("211002","辽宁省辽阳市白塔区");
        cityCodes.put("211003","辽宁省辽阳市文圣区");
        cityCodes.put("211004","辽宁省辽阳市宏伟区");
        cityCodes.put("211005","辽宁省辽阳市弓长岭区");
        cityCodes.put("211011","辽宁省辽阳市太子河区");
        cityCodes.put("211021","辽宁省辽阳市辽阳县");
        cityCodes.put("211081","辽宁省辽阳市灯塔市");
        cityCodes.put("211100","辽宁省盘锦市");
        cityCodes.put("211101","辽宁省盘锦市市辖区");
        cityCodes.put("211102","辽宁省盘锦市双台子区");
        cityCodes.put("211103","辽宁省盘锦市兴隆台区");
        cityCodes.put("211121","辽宁省盘锦市大洼县");
        cityCodes.put("211122","辽宁省盘锦市盘山县");
        cityCodes.put("211200","辽宁省铁岭市");
        cityCodes.put("211201","辽宁省铁岭市市辖区");
        cityCodes.put("211202","辽宁省铁岭市银州区");
        cityCodes.put("211204","辽宁省铁岭市清河区");
        cityCodes.put("211221","辽宁省铁岭市铁岭县");
        cityCodes.put("211223","辽宁省铁岭市西丰县");
        cityCodes.put("211224","辽宁省铁岭市昌图县");
        cityCodes.put("211281","辽宁省铁岭市铁法市");
        cityCodes.put("211282","辽宁省铁岭市开原市");
        cityCodes.put("211300","辽宁省朝阳市");
        cityCodes.put("211301","辽宁省朝阳市市辖区");
        cityCodes.put("211302","辽宁省朝阳市双塔区");
        cityCodes.put("211303","辽宁省朝阳市龙城区");
        cityCodes.put("211321","辽宁省朝阳市朝阳县");
        cityCodes.put("211322","辽宁省朝阳市建平县");
        cityCodes.put("211324","辽宁省朝阳市喀喇沁左翼蒙古族自治县");
        cityCodes.put("211381","辽宁省朝阳市北票市");
        cityCodes.put("211382","辽宁省朝阳市凌源市");
        cityCodes.put("211400","辽宁省葫芦岛市");
        cityCodes.put("211401","辽宁省葫芦岛市市辖区");
        cityCodes.put("211402","辽宁省葫芦岛市连山区");
        cityCodes.put("211403","辽宁省葫芦岛市龙港区");
        cityCodes.put("211404","辽宁省葫芦岛市南票区");
        cityCodes.put("211421","辽宁省葫芦岛市绥中县");
        cityCodes.put("211422","辽宁省葫芦岛市建昌县");
        cityCodes.put("211481","辽宁省葫芦岛市兴城市");
        cityCodes.put("220000","吉林省");
        cityCodes.put("220100","吉林省长春市");
        cityCodes.put("220101","吉林省长春市市辖区");
        cityCodes.put("220102","吉林省长春市南关区");
        cityCodes.put("220103","吉林省长春市宽城区");
        cityCodes.put("220104","吉林省长春市朝阳区");
        cityCodes.put("220105","吉林省长春市二道区");
        cityCodes.put("220106","吉林省长春市绿园区");
        cityCodes.put("220112","吉林省长春市双阳区");
        cityCodes.put("220122","吉林省长春市农安县");
        cityCodes.put("220181","吉林省长春市九台市");
        cityCodes.put("220182","吉林省长春市榆树市");
        cityCodes.put("220183","吉林省长春市德惠市");
        cityCodes.put("220200","吉林省吉林市");
        cityCodes.put("220201","吉林省吉林市市辖区");
        cityCodes.put("220202","吉林省吉林市昌邑区");
        cityCodes.put("220203","吉林省吉林市龙潭区");
        cityCodes.put("220204","吉林省吉林市船营区");
        cityCodes.put("220211","吉林省吉林市丰满区");
        cityCodes.put("220221","吉林省吉林市永吉县");
        cityCodes.put("220281","吉林省吉林市蛟河市");
        cityCodes.put("220282","吉林省吉林市桦甸市");
        cityCodes.put("220283","吉林省吉林市舒兰市");
        cityCodes.put("220284","吉林省吉林市磐石市");
        cityCodes.put("220300","吉林省四平市");
        cityCodes.put("220301","吉林省四平市市辖区");
        cityCodes.put("220302","吉林省四平市铁西区");
        cityCodes.put("220303","吉林省四平市铁东区");
        cityCodes.put("220322","吉林省四平市梨树县");
        cityCodes.put("220323","吉林省四平市伊通满族自治县");
        cityCodes.put("220381","吉林省四平市公主岭市");
        cityCodes.put("220382","吉林省四平市双辽市");
        cityCodes.put("220400","吉林省辽源市");
        cityCodes.put("220401","吉林省辽源市市辖区");
        cityCodes.put("220402","吉林省辽源市龙山区");
        cityCodes.put("220403","吉林省辽源市西安区");
        cityCodes.put("220421","吉林省辽源市东丰县");
        cityCodes.put("220422","吉林省辽源市东辽县");
        cityCodes.put("220500","吉林省通化市");
        cityCodes.put("220501","吉林省通化市市辖区");
        cityCodes.put("220502","吉林省通化市东昌区");
        cityCodes.put("220503","吉林省通化市二道江区");
        cityCodes.put("220521","吉林省通化市通化县");
        cityCodes.put("220523","吉林省通化市辉南县");
        cityCodes.put("220524","吉林省通化市柳河县");
        cityCodes.put("220581","吉林省通化市梅河口市");
        cityCodes.put("220582","吉林省通化市集安市");
        cityCodes.put("220600","吉林省白山市");
        cityCodes.put("220601","吉林省白山市市辖区");
        cityCodes.put("220602","吉林省白山市八道江区");
        cityCodes.put("220621","吉林省白山市抚松县");
        cityCodes.put("220622","吉林省白山市靖宇县");
        cityCodes.put("220623","吉林省白山市长白朝鲜族自治县");
        cityCodes.put("220625","吉林省白山市江源县");
        cityCodes.put("220681","吉林省白山市临江市");
        cityCodes.put("220700","吉林省松原市");
        cityCodes.put("220701","吉林省松原市市辖区");
        cityCodes.put("220702","吉林省松原市宁江区");
        cityCodes.put("220721","吉林省松原市前郭尔罗斯蒙古族自治县");
        cityCodes.put("220722","吉林省松原市长岭县");
        cityCodes.put("220723","吉林省松原市乾安县");
        cityCodes.put("220724","吉林省松原市扶余县");
        cityCodes.put("220800","吉林省白城市");
        cityCodes.put("220801","吉林省白城市市辖区");
        cityCodes.put("220802","吉林省白城市洮北区");
        cityCodes.put("220821","吉林省白城市镇赉县");
        cityCodes.put("220822","吉林省白城市通榆县");
        cityCodes.put("220881","吉林省白城市洮南市");
        cityCodes.put("220882","吉林省白城市大安市");
        cityCodes.put("222400","吉林省延边朝鲜族自治州");
        cityCodes.put("222401","吉林省延边朝鲜族自治州延吉市");
        cityCodes.put("222402","吉林省延边朝鲜族自治州图们市");
        cityCodes.put("222403","吉林省延边朝鲜族自治州敦化市");
        cityCodes.put("222404","吉林省延边朝鲜族自治州珲春市");
        cityCodes.put("222405","吉林省延边朝鲜族自治州龙井市");
        cityCodes.put("222406","吉林省延边朝鲜族自治州和龙市");
        cityCodes.put("222424","吉林省延边朝鲜族自治州汪清县");
        cityCodes.put("222426","吉林省延边朝鲜族自治州安图县");
        cityCodes.put("230000","黑龙江省");
        cityCodes.put("230100","黑龙江省哈尔滨市");
        cityCodes.put("230101","黑龙江省哈尔滨市市辖区");
        cityCodes.put("230102","黑龙江省哈尔滨市道里区");
        cityCodes.put("230103","黑龙江省哈尔滨市南岗区");
        cityCodes.put("230104","黑龙江省哈尔滨市道外区");
        cityCodes.put("230105","黑龙江省哈尔滨市太平区");
        cityCodes.put("230106","黑龙江省哈尔滨市香坊区");
        cityCodes.put("230107","黑龙江省哈尔滨市动力区");
        cityCodes.put("230108","黑龙江省哈尔滨市平房区");
        cityCodes.put("230121","黑龙江省哈尔滨市呼兰县");
        cityCodes.put("230123","黑龙江省哈尔滨市依兰县");
        cityCodes.put("230124","黑龙江省哈尔滨市方正县");
        cityCodes.put("230125","黑龙江省哈尔滨市宾县");
        cityCodes.put("230126","黑龙江省哈尔滨市巴彦县");
        cityCodes.put("230127","黑龙江省哈尔滨市木兰县");
        cityCodes.put("230128","黑龙江省哈尔滨市通河县");
        cityCodes.put("230129","黑龙江省哈尔滨市延寿县");
        cityCodes.put("230181","黑龙江省哈尔滨市阿城市");
        cityCodes.put("230182","黑龙江省哈尔滨市双城市");
        cityCodes.put("230183","黑龙江省哈尔滨市尚志市");
        cityCodes.put("230184","黑龙江省哈尔滨市五常市");
        cityCodes.put("230200","黑龙江省齐齐哈尔市");
        cityCodes.put("230201","黑龙江省齐齐哈尔市市辖区");
        cityCodes.put("230202","黑龙江省齐齐哈尔市龙沙区");
        cityCodes.put("230203","黑龙江省齐齐哈尔市建华区");
        cityCodes.put("230204","黑龙江省齐齐哈尔市铁锋区");
        cityCodes.put("230205","黑龙江省齐齐哈尔市昂昂溪区");
        cityCodes.put("230206","黑龙江省齐齐哈尔市富拉尔基区");
        cityCodes.put("230207","黑龙江省齐齐哈尔市碾子山区");
        cityCodes.put("230208","黑龙江省齐齐哈尔市梅里斯达斡尔族区");
        cityCodes.put("230221","黑龙江省齐齐哈尔市龙江县");
        cityCodes.put("230223","黑龙江省齐齐哈尔市依安县");
        cityCodes.put("230224","黑龙江省齐齐哈尔市泰来县");
        cityCodes.put("230225","黑龙江省齐齐哈尔市甘南县");
        cityCodes.put("230227","黑龙江省齐齐哈尔市富裕县");
        cityCodes.put("230229","黑龙江省齐齐哈尔市克山县");
        cityCodes.put("230230","黑龙江省齐齐哈尔市克东县");
        cityCodes.put("230231","黑龙江省齐齐哈尔市拜泉县");
        cityCodes.put("230281","黑龙江省齐齐哈尔市讷河市");
        cityCodes.put("230300","黑龙江省鸡西市");
        cityCodes.put("230301","黑龙江省鸡西市市辖区");
        cityCodes.put("230302","黑龙江省鸡西市鸡冠区");
        cityCodes.put("230303","黑龙江省鸡西市恒山区");
        cityCodes.put("230304","黑龙江省鸡西市滴道区");
        cityCodes.put("230305","黑龙江省鸡西市梨树区");
        cityCodes.put("230306","黑龙江省鸡西市城子河区");
        cityCodes.put("230307","黑龙江省鸡西市麻山区");
        cityCodes.put("230321","黑龙江省鸡西市鸡东县");
        cityCodes.put("230381","黑龙江省鸡西市虎林市");
        cityCodes.put("230382","黑龙江省鸡西市密山市");
        cityCodes.put("230400","黑龙江省鹤岗市");
        cityCodes.put("230401","黑龙江省鹤岗市市辖区");
        cityCodes.put("230402","黑龙江省鹤岗市向阳区");
        cityCodes.put("230403","黑龙江省鹤岗市工农区");
        cityCodes.put("230404","黑龙江省鹤岗市南山区");
        cityCodes.put("230405","黑龙江省鹤岗市兴安区");
        cityCodes.put("230406","黑龙江省鹤岗市东山区");
        cityCodes.put("230407","黑龙江省鹤岗市兴山区");
        cityCodes.put("230421","黑龙江省鹤岗市萝北县");
        cityCodes.put("230422","黑龙江省鹤岗市绥滨县");
        cityCodes.put("230500","黑龙江省双鸭山市");
        cityCodes.put("230501","黑龙江省双鸭山市市辖区");
        cityCodes.put("230502","黑龙江省双鸭山市尖山区");
        cityCodes.put("230503","黑龙江省双鸭山市岭东区");
        cityCodes.put("230505","黑龙江省双鸭山市四方台区");
        cityCodes.put("230506","黑龙江省双鸭山市宝山区");
        cityCodes.put("230521","黑龙江省双鸭山市集贤县");
        cityCodes.put("230522","黑龙江省双鸭山市友谊县");
        cityCodes.put("230523","黑龙江省双鸭山市宝清县");
        cityCodes.put("230524","黑龙江省双鸭山市饶河县");
        cityCodes.put("230600","黑龙江省大庆市");
        cityCodes.put("230601","黑龙江省大庆市市辖区");
        cityCodes.put("230602","黑龙江省大庆市萨尔图区");
        cityCodes.put("230603","黑龙江省大庆市龙凤区");
        cityCodes.put("230604","黑龙江省大庆市让胡路区");
        cityCodes.put("230605","黑龙江省大庆市红岗区");
        cityCodes.put("230606","黑龙江省大庆市大同区");
        cityCodes.put("230621","黑龙江省大庆市肇州县");
        cityCodes.put("230622","黑龙江省大庆市肇源县");
        cityCodes.put("230623","黑龙江省大庆市林甸县");
        cityCodes.put("230624","黑龙江省大庆市杜尔伯特蒙古族自治县");
        cityCodes.put("230700","黑龙江省伊春市");
        cityCodes.put("230701","黑龙江省伊春市市辖区");
        cityCodes.put("230702","黑龙江省伊春市伊春区");
        cityCodes.put("230703","黑龙江省伊春市南岔区");
        cityCodes.put("230704","黑龙江省伊春市友好区");
        cityCodes.put("230705","黑龙江省伊春市西林区");
        cityCodes.put("230706","黑龙江省伊春市翠峦区");
        cityCodes.put("230707","黑龙江省伊春市新青区");
        cityCodes.put("230708","黑龙江省伊春市美溪区");
        cityCodes.put("230709","黑龙江省伊春市金山屯区");
        cityCodes.put("230710","黑龙江省伊春市五营区");
        cityCodes.put("230711","黑龙江省伊春市乌马河区");
        cityCodes.put("230712","黑龙江省伊春市汤旺河区");
        cityCodes.put("230713","黑龙江省伊春市带岭区");
        cityCodes.put("230714","黑龙江省伊春市乌伊岭区");
        cityCodes.put("230715","黑龙江省伊春市红星区");
        cityCodes.put("230716","黑龙江省伊春市上甘岭区");
        cityCodes.put("230722","黑龙江省伊春市嘉荫县");
        cityCodes.put("230781","黑龙江省伊春市铁力市");
        cityCodes.put("230800","黑龙江省佳木斯市");
        cityCodes.put("230801","黑龙江省佳木斯市市辖区");
        cityCodes.put("230802","黑龙江省佳木斯市永红区");
        cityCodes.put("230803","黑龙江省佳木斯市向阳区");
        cityCodes.put("230804","黑龙江省佳木斯市前进区");
        cityCodes.put("230805","黑龙江省佳木斯市东风区");
        cityCodes.put("230811","黑龙江省佳木斯市郊区");
        cityCodes.put("230822","黑龙江省佳木斯市桦南县");
        cityCodes.put("230826","黑龙江省佳木斯市桦川县");
        cityCodes.put("230828","黑龙江省佳木斯市汤原县");
        cityCodes.put("230833","黑龙江省佳木斯市抚远县");
        cityCodes.put("230881","黑龙江省佳木斯市同江市");
        cityCodes.put("230882","黑龙江省佳木斯市富锦市");
        cityCodes.put("230900","黑龙江省七台河市");
        cityCodes.put("230901","黑龙江省七台河市市辖区");
        cityCodes.put("230902","黑龙江省七台河市新兴区");
        cityCodes.put("230903","黑龙江省七台河市桃山区");
        cityCodes.put("230904","黑龙江省七台河市茄子河区");
        cityCodes.put("230921","黑龙江省七台河市勃利县");
        cityCodes.put("231000","黑龙江省牡丹江市");
        cityCodes.put("231001","黑龙江省牡丹江市市辖区");
        cityCodes.put("231002","黑龙江省牡丹江市东安区");
        cityCodes.put("231003","黑龙江省牡丹江市阳明区");
        cityCodes.put("231004","黑龙江省牡丹江市爱民区");
        cityCodes.put("231005","黑龙江省牡丹江市西安区");
        cityCodes.put("231024","黑龙江省牡丹江市东宁县");
        cityCodes.put("231025","黑龙江省牡丹江市林口县");
        cityCodes.put("231081","黑龙江省牡丹江市绥芬河市");
        cityCodes.put("231083","黑龙江省牡丹江市海林市");
        cityCodes.put("231084","黑龙江省牡丹江市宁安市");
        cityCodes.put("231085","黑龙江省牡丹江市穆棱市");
        cityCodes.put("231100","黑龙江省黑河市");
        cityCodes.put("231101","黑龙江省黑河市市辖区");
        cityCodes.put("231102","黑龙江省黑河市爱辉区");
        cityCodes.put("231121","黑龙江省黑河市嫩江县");
        cityCodes.put("231123","黑龙江省黑河市逊克县");
        cityCodes.put("231124","黑龙江省黑河市孙吴县");
        cityCodes.put("231181","黑龙江省黑河市北安市");
        cityCodes.put("231182","黑龙江省黑河市五大连池市");
        cityCodes.put("232300","黑龙江省绥化地区");
        cityCodes.put("232301","黑龙江省绥化地区绥化市");
        cityCodes.put("232302","黑龙江省绥化地区安达市");
        cityCodes.put("232303","黑龙江省绥化地区肇东市");
        cityCodes.put("232304","黑龙江省绥化地区海伦市");
        cityCodes.put("232324","黑龙江省绥化地区望奎县");
        cityCodes.put("232325","黑龙江省绥化地区兰西县");
        cityCodes.put("232326","黑龙江省绥化地区青冈县");
        cityCodes.put("232330","黑龙江省绥化地区庆安县");
        cityCodes.put("232331","黑龙江省绥化地区明水县");
        cityCodes.put("232332","黑龙江省绥化地区绥棱县");
        cityCodes.put("232700","黑龙江省大兴安岭地区");
        cityCodes.put("232721","黑龙江省大兴安岭地区呼玛县");
        cityCodes.put("232722","黑龙江省大兴安岭地区塔河县");
        cityCodes.put("232723","黑龙江省大兴安岭地区漠河县");
        cityCodes.put("310000","上海市");
        cityCodes.put("310100","上海市市辖区");
        cityCodes.put("310101","上海市黄浦区");
        cityCodes.put("310102","上海市南市区");
        cityCodes.put("310103","上海市卢湾区");
        cityCodes.put("310104","上海市徐汇区");
        cityCodes.put("310105","上海市长宁区");
        cityCodes.put("310106","上海市静安区");
        cityCodes.put("310107","上海市普陀区");
        cityCodes.put("310108","上海市闸北区");
        cityCodes.put("310109","上海市虹口区");
        cityCodes.put("310110","上海市杨浦区");
        cityCodes.put("310112","上海市闵行区");
        cityCodes.put("310113","上海市宝山区");
        cityCodes.put("310114","上海市嘉定区");
        cityCodes.put("310115","上海市浦东新区");
        cityCodes.put("310116","上海市金山区");
        cityCodes.put("310117","上海市松江区");
        cityCodes.put("310200","上海市县");
        cityCodes.put("310225","上海市南汇县");
        cityCodes.put("310226","上海市奉贤县");
        cityCodes.put("310229","上海市青浦县");
        cityCodes.put("310230","上海市崇明县");
        cityCodes.put("320000","江苏省");
        cityCodes.put("320100","江苏省南京市");
        cityCodes.put("320101","江苏省南京市市辖区");
        cityCodes.put("320102","江苏省南京市玄武区");
        cityCodes.put("320103","江苏省南京市白下区");
        cityCodes.put("320104","江苏省南京市秦淮区");
        cityCodes.put("320105","江苏省南京市建邺区");
        cityCodes.put("320106","江苏省南京市鼓楼区");
        cityCodes.put("320107","江苏省南京市下关区");
        cityCodes.put("320111","江苏省南京市浦口区");
        cityCodes.put("320112","江苏省南京市大厂区");
        cityCodes.put("320113","江苏省南京市栖霞区");
        cityCodes.put("320114","江苏省南京市雨花台区");
        cityCodes.put("320121","江苏省南京市江宁县");
        cityCodes.put("320122","江苏省南京市江浦县");
        cityCodes.put("320123","江苏省南京市六合县");
        cityCodes.put("320124","江苏省南京市溧水县");
        cityCodes.put("320125","江苏省南京市高淳县");
        cityCodes.put("320200","江苏省无锡市");
        cityCodes.put("320201","江苏省无锡市市辖区");
        cityCodes.put("320202","江苏省无锡市崇安区");
        cityCodes.put("320203","江苏省无锡市南长区");
        cityCodes.put("320204","江苏省无锡市北塘区");
        cityCodes.put("320211","江苏省无锡市郊区");
        cityCodes.put("320281","江苏省无锡市江阴市");
        cityCodes.put("320282","江苏省无锡市宜兴市");
        cityCodes.put("320283","江苏省无锡市锡山市");
        cityCodes.put("320300","江苏省徐州市");
        cityCodes.put("320301","江苏省徐州市市辖区");
        cityCodes.put("320302","江苏省徐州市鼓楼区");
        cityCodes.put("320303","江苏省徐州市云龙区");
        cityCodes.put("320304","江苏省徐州市九里区");
        cityCodes.put("320305","江苏省徐州市贾汪区");
        cityCodes.put("320311","江苏省徐州市泉山区");
        cityCodes.put("320321","江苏省徐州市丰县");
        cityCodes.put("320322","江苏省徐州市沛县");
        cityCodes.put("320323","江苏省徐州市铜山县");
        cityCodes.put("320324","江苏省徐州市睢宁县");
        cityCodes.put("320381","江苏省徐州市新沂市");
        cityCodes.put("320382","江苏省徐州市邳州市");
        cityCodes.put("320400","江苏省常州市");
        cityCodes.put("320401","江苏省常州市市辖区");
        cityCodes.put("320402","江苏省常州市天宁区");
        cityCodes.put("320404","江苏省常州市钟楼区");
        cityCodes.put("320405","江苏省常州市戚墅堰区");
        cityCodes.put("320411","江苏省常州市郊区");
        cityCodes.put("320481","江苏省常州市溧阳市");
        cityCodes.put("320482","江苏省常州市金坛市");
        cityCodes.put("320483","江苏省常州市武进市");
        cityCodes.put("320500","江苏省苏州市");
        cityCodes.put("320501","江苏省苏州市市辖区");
        cityCodes.put("320502","江苏省苏州市沧浪区");
        cityCodes.put("320503","江苏省苏州市平江区");
        cityCodes.put("320504","江苏省苏州市金阊区");
        cityCodes.put("320511","江苏省苏州市郊区");
        cityCodes.put("320581","江苏省苏州市常熟市");
        cityCodes.put("320582","江苏省苏州市张家港市");
        cityCodes.put("320583","江苏省苏州市昆山市");
        cityCodes.put("320584","江苏省苏州市吴江市");
        cityCodes.put("320585","江苏省苏州市太仓市");
        cityCodes.put("320586","江苏省苏州市吴县市");
        cityCodes.put("320600","江苏省南通市");
        cityCodes.put("320601","江苏省南通市市辖区");
        cityCodes.put("320602","江苏省南通市崇川区");
        cityCodes.put("320611","江苏省南通市港闸区");
        cityCodes.put("320621","江苏省南通市海安县");
        cityCodes.put("320623","江苏省南通市如东县");
        cityCodes.put("320681","江苏省南通市启东市");
        cityCodes.put("320682","江苏省南通市如皋市");
        cityCodes.put("320683","江苏省南通市通州市");
        cityCodes.put("320684","江苏省南通市海门市");
        cityCodes.put("320700","江苏省连云港市");
        cityCodes.put("320701","江苏省连云港市市辖区");
        cityCodes.put("320703","江苏省连云港市连云区");
        cityCodes.put("320704","江苏省连云港市云台区");
        cityCodes.put("320705","江苏省连云港市新浦区");
        cityCodes.put("320706","江苏省连云港市海州区");
        cityCodes.put("320721","江苏省连云港市赣榆县");
        cityCodes.put("320722","江苏省连云港市东海县");
        cityCodes.put("320723","江苏省连云港市灌云县");
        cityCodes.put("320724","江苏省连云港市灌南县");
        cityCodes.put("320800","江苏省淮阴市");
        cityCodes.put("320801","江苏省淮阴市市辖区");
        cityCodes.put("320802","江苏省淮阴市清河区");
        cityCodes.put("320811","江苏省淮阴市清浦区");
        cityCodes.put("320821","江苏省淮阴市淮阴县");
        cityCodes.put("320826","江苏省淮阴市涟水县");
        cityCodes.put("320829","江苏省淮阴市洪泽县");
        cityCodes.put("320830","江苏省淮阴市盱眙县");
        cityCodes.put("320831","江苏省淮阴市金湖县");
        cityCodes.put("320882","江苏省淮阴市淮安市");
        cityCodes.put("320900","江苏省盐城市");
        cityCodes.put("320901","江苏省盐城市市辖区");
        cityCodes.put("320902","江苏省盐城市城区");
        cityCodes.put("320921","江苏省盐城市响水县");
        cityCodes.put("320922","江苏省盐城市滨海县");
        cityCodes.put("320923","江苏省盐城市阜宁县");
        cityCodes.put("320924","江苏省盐城市射阳县");
        cityCodes.put("320925","江苏省盐城市建湖县");
        cityCodes.put("320928","江苏省盐城市盐都县");
        cityCodes.put("320981","江苏省盐城市东台市");
        cityCodes.put("320982","江苏省盐城市大丰市");
        cityCodes.put("321000","江苏省扬州市");
        cityCodes.put("321001","江苏省扬州市市辖区");
        cityCodes.put("321002","江苏省扬州市广陵区");
        cityCodes.put("321011","江苏省扬州市郊区");
        cityCodes.put("321023","江苏省扬州市宝应县");
        cityCodes.put("321027","江苏省扬州市邗江县");
        cityCodes.put("321081","江苏省扬州市仪征市");
        cityCodes.put("321084","江苏省扬州市高邮市");
        cityCodes.put("321088","江苏省扬州市江都市");
        cityCodes.put("321100","江苏省镇江市");
        cityCodes.put("321101","江苏省镇江市市辖区");
        cityCodes.put("321102","江苏省镇江市京口区");
        cityCodes.put("321111","江苏省镇江市润州区");
        cityCodes.put("321121","江苏省镇江市丹徒县");
        cityCodes.put("321181","江苏省镇江市丹阳市");
        cityCodes.put("321182","江苏省镇江市扬中市");
        cityCodes.put("321183","江苏省镇江市句容市");
        cityCodes.put("321200","江苏省泰州市");
        cityCodes.put("321201","江苏省泰州市市辖区");
        cityCodes.put("321202","江苏省泰州市海陵区");
        cityCodes.put("321203","江苏省泰州市高港区");
        cityCodes.put("321281","江苏省泰州市兴化市");
        cityCodes.put("321282","江苏省泰州市靖江市");
        cityCodes.put("321283","江苏省泰州市泰兴市");
        cityCodes.put("321284","江苏省泰州市姜堰市");
        cityCodes.put("321300","江苏省宿迁市");
        cityCodes.put("321301","江苏省宿迁市市辖区");
        cityCodes.put("321302","江苏省宿迁市宿城区");
        cityCodes.put("321321","江苏省宿迁市宿豫县");
        cityCodes.put("321322","江苏省宿迁市沭阳县");
        cityCodes.put("321323","江苏省宿迁市泗阳县");
        cityCodes.put("321324","江苏省宿迁市泗洪县");
        cityCodes.put("330000","浙江省");
        cityCodes.put("330100","浙江省杭州市");
        cityCodes.put("330101","浙江省杭州市市辖区");
        cityCodes.put("330102","浙江省杭州市上城区");
        cityCodes.put("330103","浙江省杭州市下城区");
        cityCodes.put("330104","浙江省杭州市江干区");
        cityCodes.put("330105","浙江省杭州市拱墅区");
        cityCodes.put("330106","浙江省杭州市西湖区");
        cityCodes.put("330108","浙江省杭州市滨江区");
        cityCodes.put("330122","浙江省杭州市桐庐县");
        cityCodes.put("330127","浙江省杭州市淳安县");
        cityCodes.put("330181","浙江省杭州市萧山市");
        cityCodes.put("330182","浙江省杭州市建德市");
        cityCodes.put("330183","浙江省杭州市富阳市");
        cityCodes.put("330184","浙江省杭州市余杭市");
        cityCodes.put("330185","浙江省杭州市临安市");
        cityCodes.put("330200","浙江省宁波市");
        cityCodes.put("330201","浙江省宁波市市辖区");
        cityCodes.put("330203","浙江省宁波市海曙区");
        cityCodes.put("330204","浙江省宁波市江东区");
        cityCodes.put("330205","浙江省宁波市江北区");
        cityCodes.put("330206","浙江省宁波市北仑区");
        cityCodes.put("330211","浙江省宁波市镇海区");
        cityCodes.put("330225","浙江省宁波市象山县");
        cityCodes.put("330226","浙江省宁波市宁海县");
        cityCodes.put("330227","浙江省宁波市鄞县");
        cityCodes.put("330281","浙江省宁波市余姚市");
        cityCodes.put("330282","浙江省宁波市慈溪市");
        cityCodes.put("330283","浙江省宁波市奉化市");
        cityCodes.put("330300","浙江省温州市");
        cityCodes.put("330301","浙江省温州市市辖区");
        cityCodes.put("330302","浙江省温州市鹿城区");
        cityCodes.put("330303","浙江省温州市龙湾区");
        cityCodes.put("330304","浙江省温州市瓯海区");
        cityCodes.put("330322","浙江省温州市洞头县");
        cityCodes.put("330324","浙江省温州市永嘉县");
        cityCodes.put("330326","浙江省温州市平阳县");
        cityCodes.put("330327","浙江省温州市苍南县");
        cityCodes.put("330328","浙江省温州市文成县");
        cityCodes.put("330329","浙江省温州市泰顺县");
        cityCodes.put("330381","浙江省温州市瑞安市");
        cityCodes.put("330382","浙江省温州市乐清市");
        cityCodes.put("330400","浙江省嘉兴市");
        cityCodes.put("330401","浙江省嘉兴市市辖区");
        cityCodes.put("330402","浙江省嘉兴市秀城区");
        cityCodes.put("330411","浙江省嘉兴市郊区");
        cityCodes.put("330421","浙江省嘉兴市嘉善县");
        cityCodes.put("330424","浙江省嘉兴市海盐县");
        cityCodes.put("330481","浙江省嘉兴市海宁市");
        cityCodes.put("330482","浙江省嘉兴市平湖市");
        cityCodes.put("330483","浙江省嘉兴市桐乡市");
        cityCodes.put("330500","浙江省湖州市");
        cityCodes.put("330501","浙江省湖州市市辖区");
        cityCodes.put("330521","浙江省湖州市德清县");
        cityCodes.put("330522","浙江省湖州市长兴县");
        cityCodes.put("330523","浙江省湖州市安吉县");
        cityCodes.put("330600","浙江省绍兴市");
        cityCodes.put("330601","浙江省绍兴市市辖区");
        cityCodes.put("330602","浙江省绍兴市越城区");
        cityCodes.put("330621","浙江省绍兴市绍兴县");
        cityCodes.put("330624","浙江省绍兴市新昌县");
        cityCodes.put("330681","浙江省绍兴市诸暨市");
        cityCodes.put("330682","浙江省绍兴市上虞市");
        cityCodes.put("330683","浙江省绍兴市嵊州市");
        cityCodes.put("330700","浙江省金华市");
        cityCodes.put("330701","浙江省金华市市辖区");
        cityCodes.put("330702","浙江省金华市婺城区");
        cityCodes.put("330721","浙江省金华市金华县");
        cityCodes.put("330723","浙江省金华市武义县");
        cityCodes.put("330726","浙江省金华市浦江县");
        cityCodes.put("330727","浙江省金华市磐安县");
        cityCodes.put("330781","浙江省金华市兰溪市");
        cityCodes.put("330782","浙江省金华市义乌市");
        cityCodes.put("330783","浙江省金华市东阳市");
        cityCodes.put("330784","浙江省金华市永康市");
        cityCodes.put("330800","浙江省衢州市");
        cityCodes.put("330801","浙江省衢州市市辖区");
        cityCodes.put("330802","浙江省衢州市柯城区");
        cityCodes.put("330821","浙江省衢州市衢县");
        cityCodes.put("330822","浙江省衢州市常山县");
        cityCodes.put("330824","浙江省衢州市开化县");
        cityCodes.put("330825","浙江省衢州市龙游县");
        cityCodes.put("330881","浙江省衢州市江山市");
        cityCodes.put("330900","浙江省舟山市");
        cityCodes.put("330901","浙江省舟山市市辖区");
        cityCodes.put("330902","浙江省舟山市定海区");
        cityCodes.put("330903","浙江省舟山市普陀区");
        cityCodes.put("330921","浙江省舟山市岱山县");
        cityCodes.put("330922","浙江省舟山市嵊泗县");
        cityCodes.put("331000","浙江省台州市");
        cityCodes.put("331001","浙江省台州市市辖区");
        cityCodes.put("331002","浙江省台州市椒江区");
        cityCodes.put("331003","浙江省台州市黄岩区");
        cityCodes.put("331004","浙江省台州市路桥区");
        cityCodes.put("331021","浙江省台州市玉环县");
        cityCodes.put("331022","浙江省台州市三门县");
        cityCodes.put("331023","浙江省台州市天台县");
        cityCodes.put("331024","浙江省台州市仙居县");
        cityCodes.put("331081","浙江省台州市温岭市");
        cityCodes.put("331082","浙江省台州市临海市");
        cityCodes.put("332500","浙江省丽水地区");
        cityCodes.put("332501","浙江省丽水地区丽水市");
        cityCodes.put("332502","浙江省丽水地区龙泉市");
        cityCodes.put("332522","浙江省丽水地区青田县");
        cityCodes.put("332523","浙江省丽水地区云和县");
        cityCodes.put("332525","浙江省丽水地区庆元县");
        cityCodes.put("332526","浙江省丽水地区缙云县");
        cityCodes.put("332527","浙江省丽水地区遂昌县");
        cityCodes.put("332528","浙江省丽水地区松阳县");
        cityCodes.put("332529","浙江省丽水地区景宁畲族自治县");
        cityCodes.put("340000","安徽省");
        cityCodes.put("340100","安徽省合肥市");
        cityCodes.put("340101","安徽省合肥市市辖区");
        cityCodes.put("340102","安徽省合肥市东市区");
        cityCodes.put("340103","安徽省合肥市中市区");
        cityCodes.put("340104","安徽省合肥市西市区");
        cityCodes.put("340111","安徽省合肥市郊区");
        cityCodes.put("340121","安徽省合肥市长丰县");
        cityCodes.put("340122","安徽省合肥市肥东县");
        cityCodes.put("340123","安徽省合肥市肥西县");
        cityCodes.put("340200","安徽省芜湖市");
        cityCodes.put("340201","安徽省芜湖市市辖区");
        cityCodes.put("340202","安徽省芜湖市镜湖区");
        cityCodes.put("340203","安徽省芜湖市马塘区");
        cityCodes.put("340204","安徽省芜湖市新芜区");
        cityCodes.put("340207","安徽省芜湖市鸠江区");
        cityCodes.put("340221","安徽省芜湖市芜湖县");
        cityCodes.put("340222","安徽省芜湖市繁昌县");
        cityCodes.put("340223","安徽省芜湖市南陵县");
        cityCodes.put("340300","安徽省蚌埠市");
        cityCodes.put("340301","安徽省蚌埠市市辖区");
        cityCodes.put("340302","安徽省蚌埠市东市区");
        cityCodes.put("340303","安徽省蚌埠市中市区");
        cityCodes.put("340304","安徽省蚌埠市西市区");
        cityCodes.put("340311","安徽省蚌埠市郊区");
        cityCodes.put("340321","安徽省蚌埠市怀远县");
        cityCodes.put("340322","安徽省蚌埠市五河县");
        cityCodes.put("340323","安徽省蚌埠市固镇县");
        cityCodes.put("340400","安徽省淮南市");
        cityCodes.put("340401","安徽省淮南市市辖区");
        cityCodes.put("340402","安徽省淮南市大通区");
        cityCodes.put("340403","安徽省淮南市田家庵区");
        cityCodes.put("340404","安徽省淮南市谢家集区");
        cityCodes.put("340405","安徽省淮南市八公山区");
        cityCodes.put("340406","安徽省淮南市潘集区");
        cityCodes.put("340421","安徽省淮南市凤台县");
        cityCodes.put("340500","安徽省马鞍山市");
        cityCodes.put("340501","安徽省马鞍山市市辖区");
        cityCodes.put("340502","安徽省马鞍山市金家庄区");
        cityCodes.put("340503","安徽省马鞍山市花山区");
        cityCodes.put("340504","安徽省马鞍山市雨山区");
        cityCodes.put("340505","安徽省马鞍山市向山区");
        cityCodes.put("340521","安徽省马鞍山市当涂县");
        cityCodes.put("340600","安徽省淮北市");
        cityCodes.put("340601","安徽省淮北市市辖区");
        cityCodes.put("340602","安徽省淮北市杜集区");
        cityCodes.put("340603","安徽省淮北市相山区");
        cityCodes.put("340604","安徽省淮北市烈山区");
        cityCodes.put("340621","安徽省淮北市濉溪县");
        cityCodes.put("340700","安徽省铜陵市");
        cityCodes.put("340701","安徽省铜陵市市辖区");
        cityCodes.put("340702","安徽省铜陵市铜官山区");
        cityCodes.put("340703","安徽省铜陵市狮子山区");
        cityCodes.put("340711","安徽省铜陵市郊区");
        cityCodes.put("340721","安徽省铜陵市铜陵县");
        cityCodes.put("340800","安徽省安庆市");
        cityCodes.put("340801","安徽省安庆市市辖区");
        cityCodes.put("340802","安徽省安庆市迎江区");
        cityCodes.put("340803","安徽省安庆市大观区");
        cityCodes.put("340811","安徽省安庆市郊区");
        cityCodes.put("340822","安徽省安庆市怀宁县");
        cityCodes.put("340823","安徽省安庆市枞阳县");
        cityCodes.put("340824","安徽省安庆市潜山县");
        cityCodes.put("340825","安徽省安庆市太湖县");
        cityCodes.put("340826","安徽省安庆市宿松县");
        cityCodes.put("340827","安徽省安庆市望江县");
        cityCodes.put("340828","安徽省安庆市岳西县");
        cityCodes.put("340881","安徽省安庆市桐城市");
        cityCodes.put("341000","安徽省黄山市");
        cityCodes.put("341001","安徽省黄山市市辖区");
        cityCodes.put("341002","安徽省黄山市屯溪区");
        cityCodes.put("341003","安徽省黄山市黄山区");
        cityCodes.put("341004","安徽省黄山市徽州区");
        cityCodes.put("341021","安徽省黄山市歙县");
        cityCodes.put("341022","安徽省黄山市休宁县");
        cityCodes.put("341023","安徽省黄山市黟县");
        cityCodes.put("341024","安徽省黄山市祁门县");
        cityCodes.put("341100","安徽省滁州市");
        cityCodes.put("341101","安徽省滁州市市辖区");
        cityCodes.put("341102","安徽省滁州市琅琊区");
        cityCodes.put("341103","安徽省滁州市南谯区");
        cityCodes.put("341122","安徽省滁州市来安县");
        cityCodes.put("341124","安徽省滁州市全椒县");
        cityCodes.put("341125","安徽省滁州市定远县");
        cityCodes.put("341126","安徽省滁州市凤阳县");
        cityCodes.put("341181","安徽省滁州市天长市");
        cityCodes.put("341182","安徽省滁州市明光市");
        cityCodes.put("341200","安徽省阜阳市");
        cityCodes.put("341201","安徽省阜阳市市辖区");
        cityCodes.put("341202","安徽省阜阳市颍州区");
        cityCodes.put("341203","安徽省阜阳市颍东区");
        cityCodes.put("341204","安徽省阜阳市颍泉区");
        cityCodes.put("341221","安徽省阜阳市临泉县");
        cityCodes.put("341222","安徽省阜阳市太和县");
        cityCodes.put("341223","安徽省阜阳市涡阳县");
        cityCodes.put("341224","安徽省阜阳市蒙城县");
        cityCodes.put("341225","安徽省阜阳市阜南县");
        cityCodes.put("341226","安徽省阜阳市颍上县");
        cityCodes.put("341227","安徽省阜阳市利辛县");
        cityCodes.put("341281","安徽省阜阳市亳州市");
        cityCodes.put("341282","安徽省阜阳市界首市");
        cityCodes.put("341300","安徽省宿州市");
        cityCodes.put("341301","安徽省宿州市市辖区");
        cityCodes.put("341302","安徽省宿州市甬桥区");
        cityCodes.put("341321","安徽省宿州市砀山县");
        cityCodes.put("341322","安徽省宿州市萧县");
        cityCodes.put("341323","安徽省宿州市灵璧县");
        cityCodes.put("341324","安徽省宿州市泗县");
        cityCodes.put("342400","安徽省六安地区");
        cityCodes.put("342401","安徽省六安地区六安市");
        cityCodes.put("342422","安徽省六安地区寿县");
        cityCodes.put("342423","安徽省六安地区霍邱县");
        cityCodes.put("342425","安徽省六安地区舒城县");
        cityCodes.put("342426","安徽省六安地区金寨县");
        cityCodes.put("342427","安徽省六安地区霍山县");
        cityCodes.put("342500","安徽省宣城地区");
        cityCodes.put("342501","安徽省宣城地区宣州市");
        cityCodes.put("342502","安徽省宣城地区宁国市");
        cityCodes.put("342522","安徽省宣城地区郎溪县");
        cityCodes.put("342523","安徽省宣城地区广德县");
        cityCodes.put("342529","安徽省宣城地区泾县");
        cityCodes.put("342530","安徽省宣城地区旌德县");
        cityCodes.put("342531","安徽省宣城地区绩溪县");
        cityCodes.put("342600","安徽省巢湖地区");
        cityCodes.put("342601","安徽省巢湖地区巢湖市");
        cityCodes.put("342622","安徽省巢湖地区庐江县");
        cityCodes.put("342623","安徽省巢湖地区无为县");
        cityCodes.put("342625","安徽省巢湖地区含山县");
        cityCodes.put("342626","安徽省巢湖地区和县");
        cityCodes.put("342900","安徽省池州地区");
        cityCodes.put("342901","安徽省池州地区贵池市");
        cityCodes.put("342921","安徽省池州地区东至县");
        cityCodes.put("342922","安徽省池州地区石台县");
        cityCodes.put("342923","安徽省池州地区青阳县");
        cityCodes.put("350000","福建省");
        cityCodes.put("350100","福建省福州市");
        cityCodes.put("350101","福建省福州市市辖区");
        cityCodes.put("350102","福建省福州市鼓楼区");
        cityCodes.put("350103","福建省福州市台江区");
        cityCodes.put("350104","福建省福州市仓山区");
        cityCodes.put("350105","福建省福州市马尾区");
        cityCodes.put("350111","福建省福州市晋安区");
        cityCodes.put("350121","福建省福州市闽侯县");
        cityCodes.put("350122","福建省福州市连江县");
        cityCodes.put("350123","福建省福州市罗源县");
        cityCodes.put("350124","福建省福州市闽清县");
        cityCodes.put("350125","福建省福州市永泰县");
        cityCodes.put("350128","福建省福州市平潭县");
        cityCodes.put("350181","福建省福州市福清市");
        cityCodes.put("350182","福建省福州市长乐市");
        cityCodes.put("350200","福建省厦门市");
        cityCodes.put("350201","福建省厦门市市辖区");
        cityCodes.put("350202","福建省厦门市鼓浪屿区");
        cityCodes.put("350203","福建省厦门市思明区");
        cityCodes.put("350204","福建省厦门市开元区");
        cityCodes.put("350205","福建省厦门市杏林区");
        cityCodes.put("350206","福建省厦门市湖里区");
        cityCodes.put("350211","福建省厦门市集美区");
        cityCodes.put("350212","福建省厦门市同安区");
        cityCodes.put("350300","福建省莆田市");
        cityCodes.put("350301","福建省莆田市市辖区");
        cityCodes.put("350302","福建省莆田市城厢区");
        cityCodes.put("350303","福建省莆田市涵江区");
        cityCodes.put("350321","福建省莆田市莆田县");
        cityCodes.put("350322","福建省莆田市仙游县");
        cityCodes.put("350400","福建省三明市");
        cityCodes.put("350401","福建省三明市市辖区");
        cityCodes.put("350402","福建省三明市梅列区");
        cityCodes.put("350403","福建省三明市三元区");
        cityCodes.put("350421","福建省三明市明溪县");
        cityCodes.put("350423","福建省三明市清流县");
        cityCodes.put("350424","福建省三明市宁化县");
        cityCodes.put("350425","福建省三明市大田县");
        cityCodes.put("350426","福建省三明市尤溪县");
        cityCodes.put("350427","福建省三明市沙县");
        cityCodes.put("350428","福建省三明市将乐县");
        cityCodes.put("350429","福建省三明市泰宁县");
        cityCodes.put("350430","福建省三明市建宁县");
        cityCodes.put("350481","福建省三明市永安市");
        cityCodes.put("350500","福建省泉州市");
        cityCodes.put("350501","福建省泉州市市辖区");
        cityCodes.put("350502","福建省泉州市鲤城区");
        cityCodes.put("350503","福建省泉州市丰泽区");
        cityCodes.put("350504","福建省泉州市洛江区");
        cityCodes.put("350521","福建省泉州市惠安县");
        cityCodes.put("350524","福建省泉州市安溪县");
        cityCodes.put("350525","福建省泉州市永春县");
        cityCodes.put("350526","福建省泉州市德化县");
        cityCodes.put("350527","福建省泉州市金门县");
        cityCodes.put("350581","福建省泉州市石狮市");
        cityCodes.put("350582","福建省泉州市晋江市");
        cityCodes.put("350583","福建省泉州市南安市");
        cityCodes.put("350600","福建省漳州市");
        cityCodes.put("350601","福建省漳州市市辖区");
        cityCodes.put("350602","福建省漳州市芗城区");
        cityCodes.put("350603","福建省漳州市龙文区");
        cityCodes.put("350622","福建省漳州市云霄县");
        cityCodes.put("350623","福建省漳州市漳浦县");
        cityCodes.put("350624","福建省漳州市诏安县");
        cityCodes.put("350625","福建省漳州市长泰县");
        cityCodes.put("350626","福建省漳州市东山县");
        cityCodes.put("350627","福建省漳州市南靖县");
        cityCodes.put("350628","福建省漳州市平和县");
        cityCodes.put("350629","福建省漳州市华安县");
        cityCodes.put("350681","福建省漳州市龙海市");
        cityCodes.put("350700","福建省南平市");
        cityCodes.put("350701","福建省南平市市辖区");
        cityCodes.put("350702","福建省南平市延平区");
        cityCodes.put("350721","福建省南平市顺昌县");
        cityCodes.put("350722","福建省南平市浦城县");
        cityCodes.put("350723","福建省南平市光泽县");
        cityCodes.put("350724","福建省南平市松溪县");
        cityCodes.put("350725","福建省南平市政和县");
        cityCodes.put("350781","福建省南平市邵武市");
        cityCodes.put("350782","福建省南平市武夷山市");
        cityCodes.put("350783","福建省南平市建瓯市");
        cityCodes.put("350784","福建省南平市建阳市");
        cityCodes.put("350800","福建省龙岩市");
        cityCodes.put("350801","福建省龙岩市市辖区");
        cityCodes.put("350802","福建省龙岩市新罗区");
        cityCodes.put("350821","福建省龙岩市长汀县");
        cityCodes.put("350822","福建省龙岩市永定县");
        cityCodes.put("350823","福建省龙岩市上杭县");
        cityCodes.put("350824","福建省龙岩市武平县");
        cityCodes.put("350825","福建省龙岩市连城县");
        cityCodes.put("350881","福建省龙岩市漳平市");
        cityCodes.put("352200","福建省宁德地区");
        cityCodes.put("352201","福建省宁德地区宁德市");
        cityCodes.put("352202","福建省宁德地区福安市");
        cityCodes.put("352203","福建省宁德地区福鼎市");
        cityCodes.put("352225","福建省宁德地区霞浦县");
        cityCodes.put("352227","福建省宁德地区古田县");
        cityCodes.put("352228","福建省宁德地区屏南县");
        cityCodes.put("352229","福建省宁德地区寿宁县");
        cityCodes.put("352230","福建省宁德地区周宁县");
        cityCodes.put("352231","福建省宁德地区柘荣县");
        cityCodes.put("360000","江西省");
        cityCodes.put("360100","江西省南昌市");
        cityCodes.put("360101","江西省南昌市市辖区");
        cityCodes.put("360102","江西省南昌市东湖区");
        cityCodes.put("360103","江西省南昌市西湖区");
        cityCodes.put("360104","江西省南昌市青云谱区");
        cityCodes.put("360105","江西省南昌市湾里区");
        cityCodes.put("360111","江西省南昌市郊区");
        cityCodes.put("360121","江西省南昌市南昌县");
        cityCodes.put("360122","江西省南昌市新建县");
        cityCodes.put("360123","江西省南昌市安义县");
        cityCodes.put("360124","江西省南昌市进贤县");
        cityCodes.put("360200","江西省景德镇市");
        cityCodes.put("360201","江西省景德镇市市辖区");
        cityCodes.put("360202","江西省景德镇市昌江区");
        cityCodes.put("360203","江西省景德镇市珠山区");
        cityCodes.put("360222","江西省景德镇市浮梁县");
        cityCodes.put("360281","江西省景德镇市乐平市");
        cityCodes.put("360300","江西省萍乡市");
        cityCodes.put("360301","江西省萍乡市市辖区");
        cityCodes.put("360302","江西省萍乡市安源区");
        cityCodes.put("360313","江西省萍乡市湘东区");
        cityCodes.put("360321","江西省萍乡市莲花县");
        cityCodes.put("360322","江西省萍乡市上栗县");
        cityCodes.put("360323","江西省萍乡市芦溪县");
        cityCodes.put("360400","江西省九江市");
        cityCodes.put("360401","江西省九江市市辖区");
        cityCodes.put("360402","江西省九江市庐山区");
        cityCodes.put("360403","江西省九江市浔阳区");
        cityCodes.put("360421","江西省九江市九江县");
        cityCodes.put("360423","江西省九江市武宁县");
        cityCodes.put("360424","江西省九江市修水县");
        cityCodes.put("360425","江西省九江市永修县");
        cityCodes.put("360426","江西省九江市德安县");
        cityCodes.put("360427","江西省九江市星子县");
        cityCodes.put("360428","江西省九江市都昌县");
        cityCodes.put("360429","江西省九江市湖口县");
        cityCodes.put("360430","江西省九江市彭泽县");
        cityCodes.put("360481","江西省九江市瑞昌市");
        cityCodes.put("360500","江西省新余市");
        cityCodes.put("360501","江西省新余市市辖区");
        cityCodes.put("360502","江西省新余市渝水区");
        cityCodes.put("360521","江西省新余市分宜县");
        cityCodes.put("360600","江西省鹰潭市");
        cityCodes.put("360601","江西省鹰潭市市辖区");
        cityCodes.put("360602","江西省鹰潭市月湖区");
        cityCodes.put("360622","江西省鹰潭市余江县");
        cityCodes.put("360681","江西省鹰潭市贵溪市");
        cityCodes.put("360700","江西省赣州市");
        cityCodes.put("360701","江西省赣州市市辖区");
        cityCodes.put("360702","江西省赣州市章贡区");
        cityCodes.put("360721","江西省赣州市赣县");
        cityCodes.put("360722","江西省赣州市信丰县");
        cityCodes.put("360723","江西省赣州市大余县");
        cityCodes.put("360724","江西省赣州市上犹县");
        cityCodes.put("360725","江西省赣州市崇义县");
        cityCodes.put("360726","江西省赣州市安远县");
        cityCodes.put("360727","江西省赣州市龙南县");
        cityCodes.put("360728","江西省赣州市定南县");
        cityCodes.put("360729","江西省赣州市全南县");
        cityCodes.put("360730","江西省赣州市宁都县");
        cityCodes.put("360731","江西省赣州市于都县");
        cityCodes.put("360732","江西省赣州市兴国县");
        cityCodes.put("360733","江西省赣州市会昌县");
        cityCodes.put("360734","江西省赣州市寻乌县");
        cityCodes.put("360735","江西省赣州市石城县");
        cityCodes.put("360781","江西省赣州市瑞金市");
        cityCodes.put("360782","江西省赣州市南康市");
        cityCodes.put("362200","江西省宜春地区");
        cityCodes.put("362201","江西省宜春地区宜春市");
        cityCodes.put("362202","江西省宜春地区丰城市");
        cityCodes.put("362203","江西省宜春地区樟树市");
        cityCodes.put("362204","江西省宜春地区高安市");
        cityCodes.put("362226","江西省宜春地区奉新县");
        cityCodes.put("362227","江西省宜春地区万载县");
        cityCodes.put("362228","江西省宜春地区上高县");
        cityCodes.put("362229","江西省宜春地区宜丰县");
        cityCodes.put("362232","江西省宜春地区靖安县");
        cityCodes.put("362233","江西省宜春地区铜鼓县");
        cityCodes.put("362300","江西省上饶地区");
        cityCodes.put("362301","江西省上饶地区上饶市");
        cityCodes.put("362302","江西省上饶地区德兴市");
        cityCodes.put("362321","江西省上饶地区上饶县");
        cityCodes.put("362322","江西省上饶地区广丰县");
        cityCodes.put("362323","江西省上饶地区玉山县");
        cityCodes.put("362324","江西省上饶地区铅山县");
        cityCodes.put("362325","江西省上饶地区横峰县");
        cityCodes.put("362326","江西省上饶地区弋阳县");
        cityCodes.put("362329","江西省上饶地区余干县");
        cityCodes.put("362330","江西省上饶地区波阳县");
        cityCodes.put("362331","江西省上饶地区万年县");
        cityCodes.put("362334","江西省上饶地区婺源县");
        cityCodes.put("362400","江西省吉安地区");
        cityCodes.put("362401","江西省吉安地区吉安市");
        cityCodes.put("362402","江西省吉安地区井冈山市");
        cityCodes.put("362421","江西省吉安地区吉安县");
        cityCodes.put("362422","江西省吉安地区吉水县");
        cityCodes.put("362423","江西省吉安地区峡江县");
        cityCodes.put("362424","江西省吉安地区新干县");
        cityCodes.put("362425","江西省吉安地区永丰县");
        cityCodes.put("362426","江西省吉安地区泰和县");
        cityCodes.put("362427","江西省吉安地区遂川县");
        cityCodes.put("362428","江西省吉安地区万安县");
        cityCodes.put("362429","江西省吉安地区安福县");
        cityCodes.put("362430","江西省吉安地区永新县");
        cityCodes.put("362432","江西省吉安地区宁冈县");
        cityCodes.put("362500","江西省抚州地区");
        cityCodes.put("362502","江西省抚州地区临川市");
        cityCodes.put("362522","江西省抚州地区南城县");
        cityCodes.put("362523","江西省抚州地区黎川县");
        cityCodes.put("362524","江西省抚州地区南丰县");
        cityCodes.put("362525","江西省抚州地区崇仁县");
        cityCodes.put("362526","江西省抚州地区乐安县");
        cityCodes.put("362527","江西省抚州地区宜黄县");
        cityCodes.put("362528","江西省抚州地区金溪县");
        cityCodes.put("362529","江西省抚州地区资溪县");
        cityCodes.put("362531","江西省抚州地区东乡县");
        cityCodes.put("362532","江西省抚州地区广昌县");
        cityCodes.put("370000","山东省");
        cityCodes.put("370100","山东省济南市");
        cityCodes.put("370101","山东省济南市市辖区");
        cityCodes.put("370102","山东省济南市历下区");
        cityCodes.put("370103","山东省济南市市中区");
        cityCodes.put("370104","山东省济南市槐荫区");
        cityCodes.put("370105","山东省济南市天桥区");
        cityCodes.put("370112","山东省济南市历城区");
        cityCodes.put("370123","山东省济南市长清县");
        cityCodes.put("370124","山东省济南市平阴县");
        cityCodes.put("370125","山东省济南市济阳县");
        cityCodes.put("370126","山东省济南市商河县");
        cityCodes.put("370181","山东省济南市章丘市");
        cityCodes.put("370200","山东省青岛市");
        cityCodes.put("370201","山东省青岛市市辖区");
        cityCodes.put("370202","山东省青岛市市南区");
        cityCodes.put("370203","山东省青岛市市北区");
        cityCodes.put("370205","山东省青岛市四方区");
        cityCodes.put("370211","山东省青岛市黄岛区");
        cityCodes.put("370212","山东省青岛市崂山区");
        cityCodes.put("370213","山东省青岛市李沧区");
        cityCodes.put("370214","山东省青岛市城阳区");
        cityCodes.put("370281","山东省青岛市胶州市");
        cityCodes.put("370282","山东省青岛市即墨市");
        cityCodes.put("370283","山东省青岛市平度市");
        cityCodes.put("370284","山东省青岛市胶南市");
        cityCodes.put("370285","山东省青岛市莱西市");
        cityCodes.put("370300","山东省淄博市");
        cityCodes.put("370301","山东省淄博市市辖区");
        cityCodes.put("370302","山东省淄博市淄川区");
        cityCodes.put("370303","山东省淄博市张店区");
        cityCodes.put("370304","山东省淄博市博山区");
        cityCodes.put("370305","山东省淄博市临淄区");
        cityCodes.put("370306","山东省淄博市周村区");
        cityCodes.put("370321","山东省淄博市桓台县");
        cityCodes.put("370322","山东省淄博市高青县");
        cityCodes.put("370323","山东省淄博市沂源县");
        cityCodes.put("370400","山东省枣庄市");
        cityCodes.put("370401","山东省枣庄市市辖区");
        cityCodes.put("370402","山东省枣庄市市中区");
        cityCodes.put("370403","山东省枣庄市薛城区");
        cityCodes.put("370404","山东省枣庄市峄城区");
        cityCodes.put("370405","山东省枣庄市台儿庄区");
        cityCodes.put("370406","山东省枣庄市山亭区");
        cityCodes.put("370481","山东省枣庄市滕州市");
        cityCodes.put("370500","山东省东营市");
        cityCodes.put("370501","山东省东营市市辖区");
        cityCodes.put("370502","山东省东营市东营区");
        cityCodes.put("370503","山东省东营市河口区");
        cityCodes.put("370521","山东省东营市垦利县");
        cityCodes.put("370522","山东省东营市利津县");
        cityCodes.put("370523","山东省东营市广饶县");
        cityCodes.put("370600","山东省烟台市");
        cityCodes.put("370601","山东省烟台市市辖区");
        cityCodes.put("370602","山东省烟台市芝罘区");
        cityCodes.put("370611","山东省烟台市福山区");
        cityCodes.put("370612","山东省烟台市牟平区");
        cityCodes.put("370613","山东省烟台市莱山区");
        cityCodes.put("370634","山东省烟台市长岛县");
        cityCodes.put("370681","山东省烟台市龙口市");
        cityCodes.put("370682","山东省烟台市莱阳市");
        cityCodes.put("370683","山东省烟台市莱州市");
        cityCodes.put("370684","山东省烟台市蓬莱市");
        cityCodes.put("370685","山东省烟台市招远市");
        cityCodes.put("370686","山东省烟台市栖霞市");
        cityCodes.put("370687","山东省烟台市海阳市");
        cityCodes.put("370700","山东省潍坊市");
        cityCodes.put("370701","山东省潍坊市市辖区");
        cityCodes.put("370702","山东省潍坊市潍城区");
        cityCodes.put("370703","山东省潍坊市寒亭区");
        cityCodes.put("370704","山东省潍坊市坊子区");
        cityCodes.put("370705","山东省潍坊市奎文区");
        cityCodes.put("370724","山东省潍坊市临朐县");
        cityCodes.put("370725","山东省潍坊市昌乐县");
        cityCodes.put("370781","山东省潍坊市青州市");
        cityCodes.put("370782","山东省潍坊市诸城市");
        cityCodes.put("370783","山东省潍坊市寿光市");
        cityCodes.put("370784","山东省潍坊市安丘市");
        cityCodes.put("370785","山东省潍坊市高密市");
        cityCodes.put("370786","山东省潍坊市昌邑市");
        cityCodes.put("370800","山东省济宁市");
        cityCodes.put("370801","山东省济宁市市辖区");
        cityCodes.put("370802","山东省济宁市市中区");
        cityCodes.put("370811","山东省济宁市任城区");
        cityCodes.put("370826","山东省济宁市微山县");
        cityCodes.put("370827","山东省济宁市鱼台县");
        cityCodes.put("370828","山东省济宁市金乡县");
        cityCodes.put("370829","山东省济宁市嘉祥县");
        cityCodes.put("370830","山东省济宁市汶上县");
        cityCodes.put("370831","山东省济宁市泗水县");
        cityCodes.put("370832","山东省济宁市梁山县");
        cityCodes.put("370881","山东省济宁市曲阜市");
        cityCodes.put("370882","山东省济宁市兖州市");
        cityCodes.put("370883","山东省济宁市邹城市");
        cityCodes.put("370900","山东省泰安市");
        cityCodes.put("370901","山东省泰安市市辖区");
        cityCodes.put("370902","山东省泰安市泰山区");
        cityCodes.put("370911","山东省泰安市郊区");
        cityCodes.put("370921","山东省泰安市宁阳县");
        cityCodes.put("370923","山东省泰安市东平县");
        cityCodes.put("370982","山东省泰安市新泰市");
        cityCodes.put("370983","山东省泰安市肥城市");
        cityCodes.put("371000","山东省威海市");
        cityCodes.put("371001","山东省威海市市辖区");
        cityCodes.put("371002","山东省威海市环翠区");
        cityCodes.put("371081","山东省威海市文登市");
        cityCodes.put("371082","山东省威海市荣成市");
        cityCodes.put("371083","山东省威海市乳山市");
        cityCodes.put("371100","山东省日照市");
        cityCodes.put("371101","山东省日照市市辖区");
        cityCodes.put("371102","山东省日照市东港区");
        cityCodes.put("371121","山东省日照市五莲县");
        cityCodes.put("371122","山东省日照市莒县");
        cityCodes.put("371200","山东省莱芜市");
        cityCodes.put("371201","山东省莱芜市市辖区");
        cityCodes.put("371202","山东省莱芜市莱城区");
        cityCodes.put("371203","山东省莱芜市钢城区");
        cityCodes.put("371300","山东省临沂市");
        cityCodes.put("371301","山东省临沂市市辖区");
        cityCodes.put("371302","山东省临沂市兰山区");
        cityCodes.put("371311","山东省临沂市罗庄区");
        cityCodes.put("371312","山东省临沂市河东区");
        cityCodes.put("371321","山东省临沂市沂南县");
        cityCodes.put("371322","山东省临沂市郯城县");
        cityCodes.put("371323","山东省临沂市沂水县");
        cityCodes.put("371324","山东省临沂市苍山县");
        cityCodes.put("371325","山东省临沂市费县");
        cityCodes.put("371326","山东省临沂市平邑县");
        cityCodes.put("371327","山东省临沂市莒南县");
        cityCodes.put("371328","山东省临沂市蒙阴县");
        cityCodes.put("371329","山东省临沂市临沭县");
        cityCodes.put("371400","山东省德州市");
        cityCodes.put("371401","山东省德州市市辖区");
        cityCodes.put("371402","山东省德州市德城区");
        cityCodes.put("371421","山东省德州市陵县");
        cityCodes.put("371422","山东省德州市宁津县");
        cityCodes.put("371423","山东省德州市庆云县");
        cityCodes.put("371424","山东省德州市临邑县");
        cityCodes.put("371425","山东省德州市齐河县");
        cityCodes.put("371426","山东省德州市平原县");
        cityCodes.put("371427","山东省德州市夏津县");
        cityCodes.put("371428","山东省德州市武城县");
        cityCodes.put("371481","山东省德州市乐陵市");
        cityCodes.put("371482","山东省德州市禹城市");
        cityCodes.put("371500","山东省聊城市");
        cityCodes.put("371501","山东省聊城市市辖区");
        cityCodes.put("371502","山东省聊城市东昌府区");
        cityCodes.put("371521","山东省聊城市阳谷县");
        cityCodes.put("371522","山东省聊城市莘县");
        cityCodes.put("371523","山东省聊城市茌平县");
        cityCodes.put("371524","山东省聊城市东阿县");
        cityCodes.put("371525","山东省聊城市冠县");
        cityCodes.put("371526","山东省聊城市高唐县");
        cityCodes.put("371581","山东省聊城市临清市");
        cityCodes.put("372300","山东省滨州地区");
        cityCodes.put("372301","山东省滨州地区滨州市");
        cityCodes.put("372321","山东省滨州地区惠民县");
        cityCodes.put("372323","山东省滨州地区阳信县");
        cityCodes.put("372324","山东省滨州地区无棣县");
        cityCodes.put("372325","山东省滨州地区沾化县");
        cityCodes.put("372328","山东省滨州地区博兴县");
        cityCodes.put("372330","山东省滨州地区邹平县");
        cityCodes.put("372900","山东省菏泽地区");
        cityCodes.put("372901","山东省菏泽地区菏泽市");
        cityCodes.put("372922","山东省菏泽地区曹县");
        cityCodes.put("372923","山东省菏泽地区定陶县");
        cityCodes.put("372924","山东省菏泽地区成武县");
        cityCodes.put("372925","山东省菏泽地区单县");
        cityCodes.put("372926","山东省菏泽地区巨野县");
        cityCodes.put("372928","山东省菏泽地区郓城县");
        cityCodes.put("372929","山东省菏泽地区鄄城县");
        cityCodes.put("372930","山东省菏泽地区东明县");
        cityCodes.put("410000","河南省");
        cityCodes.put("410100","河南省郑州市");
        cityCodes.put("410101","河南省郑州市市辖区");
        cityCodes.put("410102","河南省郑州市中原区");
        cityCodes.put("410103","河南省郑州市二七区");
        cityCodes.put("410104","河南省郑州市管城回族区");
        cityCodes.put("410105","河南省郑州市金水区");
        cityCodes.put("410106","河南省郑州市上街区");
        cityCodes.put("410108","河南省郑州市邙山区");
        cityCodes.put("410122","河南省郑州市中牟县");
        cityCodes.put("410181","河南省郑州市巩义市");
        cityCodes.put("410182","河南省郑州市荥阳市");
        cityCodes.put("410183","河南省郑州市新密市");
        cityCodes.put("410184","河南省郑州市新郑市");
        cityCodes.put("410185","河南省郑州市登封市");
        cityCodes.put("410200","河南省开封市");
        cityCodes.put("410201","河南省开封市市辖区");
        cityCodes.put("410202","河南省开封市龙亭区");
        cityCodes.put("410203","河南省开封市顺河回族区");
        cityCodes.put("410204","河南省开封市鼓楼区");
        cityCodes.put("410205","河南省开封市南关区");
        cityCodes.put("410211","河南省开封市郊区");
        cityCodes.put("410221","河南省开封市杞县");
        cityCodes.put("410222","河南省开封市通许县");
        cityCodes.put("410223","河南省开封市尉氏县");
        cityCodes.put("410224","河南省开封市开封县");
        cityCodes.put("410225","河南省开封市兰考县");
        cityCodes.put("410300","河南省洛阳市");
        cityCodes.put("410301","河南省洛阳市市辖区");
        cityCodes.put("410302","河南省洛阳市老城区");
        cityCodes.put("410303","河南省洛阳市西工区");
        cityCodes.put("410304","河南省洛阳市廛河回族区");
        cityCodes.put("410305","河南省洛阳市涧西区");
        cityCodes.put("410306","河南省洛阳市吉利区");
        cityCodes.put("410311","河南省洛阳市郊区");
        cityCodes.put("410322","河南省洛阳市孟津县");
        cityCodes.put("410323","河南省洛阳市新安县");
        cityCodes.put("410324","河南省洛阳市栾川县");
        cityCodes.put("410325","河南省洛阳市嵩县");
        cityCodes.put("410326","河南省洛阳市汝阳县");
        cityCodes.put("410327","河南省洛阳市宜阳县");
        cityCodes.put("410328","河南省洛阳市洛宁县");
        cityCodes.put("410329","河南省洛阳市伊川县");
        cityCodes.put("410381","河南省洛阳市偃师市");
        cityCodes.put("410400","河南省平顶山市");
        cityCodes.put("410401","河南省平顶山市市辖区");
        cityCodes.put("410402","河南省平顶山市新华区");
        cityCodes.put("410403","河南省平顶山市卫东区");
        cityCodes.put("410404","河南省平顶山市石龙区");
        cityCodes.put("410411","河南省平顶山市湛河区");
        cityCodes.put("410421","河南省平顶山市宝丰县");
        cityCodes.put("410422","河南省平顶山市叶县");
        cityCodes.put("410423","河南省平顶山市鲁山县");
        cityCodes.put("410425","河南省平顶山市郏县");
        cityCodes.put("410481","河南省平顶山市舞钢市");
        cityCodes.put("410482","河南省平顶山市汝州市");
        cityCodes.put("410500","河南省安阳市");
        cityCodes.put("410501","河南省安阳市市辖区");
        cityCodes.put("410502","河南省安阳市文峰区");
        cityCodes.put("410503","河南省安阳市北关区");
        cityCodes.put("410504","河南省安阳市铁西区");
        cityCodes.put("410511","河南省安阳市郊区");
        cityCodes.put("410522","河南省安阳市安阳县");
        cityCodes.put("410523","河南省安阳市汤阴县");
        cityCodes.put("410526","河南省安阳市滑县");
        cityCodes.put("410527","河南省安阳市内黄县");
        cityCodes.put("410581","河南省安阳市林州市");
        cityCodes.put("410600","河南省鹤壁市");
        cityCodes.put("410601","河南省鹤壁市市辖区");
        cityCodes.put("410602","河南省鹤壁市鹤山区");
        cityCodes.put("410603","河南省鹤壁市山城区");
        cityCodes.put("410611","河南省鹤壁市郊区");
        cityCodes.put("410621","河南省鹤壁市浚县");
        cityCodes.put("410622","河南省鹤壁市淇县");
        cityCodes.put("410700","河南省新乡市");
        cityCodes.put("410701","河南省新乡市市辖区");
        cityCodes.put("410702","河南省新乡市红旗区");
        cityCodes.put("410703","河南省新乡市新华区");
        cityCodes.put("410704","河南省新乡市北站区");
        cityCodes.put("410711","河南省新乡市郊区");
        cityCodes.put("410721","河南省新乡市新乡县");
        cityCodes.put("410724","河南省新乡市获嘉县");
        cityCodes.put("410725","河南省新乡市原阳县");
        cityCodes.put("410726","河南省新乡市延津县");
        cityCodes.put("410727","河南省新乡市封丘县");
        cityCodes.put("410728","河南省新乡市长垣县");
        cityCodes.put("410781","河南省新乡市卫辉市");
        cityCodes.put("410782","河南省新乡市辉县市");
        cityCodes.put("410800","河南省焦作市");
        cityCodes.put("410801","河南省焦作市市辖区");
        cityCodes.put("410802","河南省焦作市解放区");
        cityCodes.put("410803","河南省焦作市中站区");
        cityCodes.put("410804","河南省焦作市马村区");
        cityCodes.put("410811","河南省焦作市山阳区");
        cityCodes.put("410821","河南省焦作市修武县");
        cityCodes.put("410822","河南省焦作市博爱县");
        cityCodes.put("410823","河南省焦作市武陟县");
        cityCodes.put("410825","河南省焦作市温县");
        cityCodes.put("410881","河南省焦作市济源市");
        cityCodes.put("410882","河南省焦作市沁阳市");
        cityCodes.put("410883","河南省焦作市孟州市");
        cityCodes.put("410900","河南省濮阳市");
        cityCodes.put("410901","河南省濮阳市市辖区");
        cityCodes.put("410902","河南省濮阳市市区");
        cityCodes.put("410922","河南省濮阳市清丰县");
        cityCodes.put("410923","河南省濮阳市南乐县");
        cityCodes.put("410926","河南省濮阳市范县");
        cityCodes.put("410927","河南省濮阳市台前县");
        cityCodes.put("410928","河南省濮阳市濮阳县");
        cityCodes.put("411000","河南省许昌市");
        cityCodes.put("411001","河南省许昌市市辖区");
        cityCodes.put("411002","河南省许昌市魏都区");
        cityCodes.put("411023","河南省许昌市许昌县");
        cityCodes.put("411024","河南省许昌市鄢陵县");
        cityCodes.put("411025","河南省许昌市襄城县");
        cityCodes.put("411081","河南省许昌市禹州市");
        cityCodes.put("411082","河南省许昌市长葛市");
        cityCodes.put("411100","河南省漯河市");
        cityCodes.put("411101","河南省漯河市市辖区");
        cityCodes.put("411102","河南省漯河市源汇区");
        cityCodes.put("411121","河南省漯河市舞阳县");
        cityCodes.put("411122","河南省漯河市临颍县");
        cityCodes.put("411123","河南省漯河市郾城县");
        cityCodes.put("411200","河南省三门峡市");
        cityCodes.put("411201","河南省三门峡市市辖区");
        cityCodes.put("411202","河南省三门峡市湖滨区");
        cityCodes.put("411221","河南省三门峡市渑池县");
        cityCodes.put("411222","河南省三门峡市陕县");
        cityCodes.put("411224","河南省三门峡市卢氏县");
        cityCodes.put("411281","河南省三门峡市义马市");
        cityCodes.put("411282","河南省三门峡市灵宝市");
        cityCodes.put("411300","河南省南阳市");
        cityCodes.put("411301","河南省南阳市市辖区");
        cityCodes.put("411302","河南省南阳市宛城区");
        cityCodes.put("411303","河南省南阳市卧龙区");
        cityCodes.put("411321","河南省南阳市南召县");
        cityCodes.put("411322","河南省南阳市方城县");
        cityCodes.put("411323","河南省南阳市西峡县");
        cityCodes.put("411324","河南省南阳市镇平县");
        cityCodes.put("411325","河南省南阳市内乡县");
        cityCodes.put("411326","河南省南阳市淅川县");
        cityCodes.put("411327","河南省南阳市社旗县");
        cityCodes.put("411328","河南省南阳市唐河县");
        cityCodes.put("411329","河南省南阳市新野县");
        cityCodes.put("411330","河南省南阳市桐柏县");
        cityCodes.put("411381","河南省南阳市邓州市");
        cityCodes.put("411400","河南省商丘市");
        cityCodes.put("411401","河南省商丘市市辖区");
        cityCodes.put("411402","河南省商丘市梁园区");
        cityCodes.put("411403","河南省商丘市睢阳区");
        cityCodes.put("411421","河南省商丘市民权县");
        cityCodes.put("411422","河南省商丘市睢县");
        cityCodes.put("411423","河南省商丘市宁陵县");
        cityCodes.put("411424","河南省商丘市柘城县");
        cityCodes.put("411425","河南省商丘市虞城县");
        cityCodes.put("411426","河南省商丘市夏邑县");
        cityCodes.put("411481","河南省商丘市永城市");
        cityCodes.put("411500","河南省信阳市");
        cityCodes.put("411501","河南省信阳市市辖区");
        cityCodes.put("411502","河南省信阳市师河区");
        cityCodes.put("411503","河南省信阳市平桥区");
        cityCodes.put("411521","河南省信阳市罗山县");
        cityCodes.put("411522","河南省信阳市光山县");
        cityCodes.put("411523","河南省信阳市新县");
        cityCodes.put("411524","河南省信阳市商城县");
        cityCodes.put("411525","河南省信阳市固始县");
        cityCodes.put("411526","河南省信阳市潢川县");
        cityCodes.put("411527","河南省信阳市淮滨县");
        cityCodes.put("411528","河南省信阳市息县");
        cityCodes.put("412700","河南省周口地区");
        cityCodes.put("412701","河南省周口地区周口市");
        cityCodes.put("412702","河南省周口地区项城市");
        cityCodes.put("412721","河南省周口地区扶沟县");
        cityCodes.put("412722","河南省周口地区西华县");
        cityCodes.put("412723","河南省周口地区商水县");
        cityCodes.put("412724","河南省周口地区太康县");
        cityCodes.put("412725","河南省周口地区鹿邑县");
        cityCodes.put("412726","河南省周口地区郸城县");
        cityCodes.put("412727","河南省周口地区淮阳县");
        cityCodes.put("412728","河南省周口地区沈丘县");
        cityCodes.put("412800","河南省驻马店地区");
        cityCodes.put("412801","河南省驻马店地区驻马店市");
        cityCodes.put("412821","河南省驻马店地区确山县");
        cityCodes.put("412822","河南省驻马店地区泌阳县");
        cityCodes.put("412823","河南省驻马店地区遂平县");
        cityCodes.put("412824","河南省驻马店地区西平县");
        cityCodes.put("412825","河南省驻马店地区上蔡县");
        cityCodes.put("412826","河南省驻马店地区汝南县");
        cityCodes.put("412827","河南省驻马店地区平舆县");
        cityCodes.put("412828","河南省驻马店地区新蔡县");
        cityCodes.put("412829","河南省驻马店地区正阳县");
        cityCodes.put("420000","湖北省");
        cityCodes.put("420100","湖北省武汉市");
        cityCodes.put("420101","湖北省武汉市市辖区");
        cityCodes.put("420102","湖北省武汉市江岸区");
        cityCodes.put("420103","湖北省武汉市江汉区");
        cityCodes.put("420104","湖北省武汉市乔口区");
        cityCodes.put("420105","湖北省武汉市汉阳区");
        cityCodes.put("420106","湖北省武汉市武昌区");
        cityCodes.put("420107","湖北省武汉市青山区");
        cityCodes.put("420111","湖北省武汉市洪山区");
        cityCodes.put("420112","湖北省武汉市东西湖区");
        cityCodes.put("420113","湖北省武汉市汉南区");
        cityCodes.put("420114","湖北省武汉市蔡甸区");
        cityCodes.put("420115","湖北省武汉市江夏区");
        cityCodes.put("420116","湖北省武汉市黄陂区");
        cityCodes.put("420117","湖北省武汉市新洲区");
        cityCodes.put("420200","湖北省黄石市");
        cityCodes.put("420201","湖北省黄石市市辖区");
        cityCodes.put("420202","湖北省黄石市黄石港区");
        cityCodes.put("420203","湖北省黄石市石灰窑区");
        cityCodes.put("420204","湖北省黄石市下陆区");
        cityCodes.put("420205","湖北省黄石市铁山区");
        cityCodes.put("420222","湖北省黄石市阳新县");
        cityCodes.put("420281","湖北省黄石市大冶市");
        cityCodes.put("420300","湖北省十堰市");
        cityCodes.put("420301","湖北省十堰市市辖区");
        cityCodes.put("420302","湖北省十堰市茅箭区");
        cityCodes.put("420303","湖北省十堰市张湾区");
        cityCodes.put("420321","湖北省十堰市郧县");
        cityCodes.put("420322","湖北省十堰市郧西县");
        cityCodes.put("420323","湖北省十堰市竹山县");
        cityCodes.put("420324","湖北省十堰市竹溪县");
        cityCodes.put("420325","湖北省十堰市房县");
        cityCodes.put("420381","湖北省十堰市丹江口市");
        cityCodes.put("420500","湖北省宜昌市");
        cityCodes.put("420501","湖北省宜昌市市辖区");
        cityCodes.put("420502","湖北省宜昌市西陵区");
        cityCodes.put("420503","湖北省宜昌市伍家岗区");
        cityCodes.put("420504","湖北省宜昌市点军区");
        cityCodes.put("420505","湖北省宜昌市虎亭区");
        cityCodes.put("420521","湖北省宜昌市宜昌县");
        cityCodes.put("420525","湖北省宜昌市远安县");
        cityCodes.put("420526","湖北省宜昌市兴山县");
        cityCodes.put("420527","湖北省宜昌市秭归县");
        cityCodes.put("420528","湖北省宜昌市长阳土家族自治县");
        cityCodes.put("420529","湖北省宜昌市五峰土家族自治县");
        cityCodes.put("420581","湖北省宜昌市宜都市");
        cityCodes.put("420582","湖北省宜昌市当阳市");
        cityCodes.put("420583","湖北省宜昌市枝江市");
        cityCodes.put("420600","湖北省襄樊市");
        cityCodes.put("420601","湖北省襄樊市市辖区");
        cityCodes.put("420602","湖北省襄樊市襄城区");
        cityCodes.put("420606","湖北省襄樊市樊城区");
        cityCodes.put("420621","湖北省襄樊市襄阳县");
        cityCodes.put("420624","湖北省襄樊市南漳县");
        cityCodes.put("420625","湖北省襄樊市谷城县");
        cityCodes.put("420626","湖北省襄樊市保康县");
        cityCodes.put("420682","湖北省襄樊市老河口市");
        cityCodes.put("420683","湖北省襄樊市枣阳市");
        cityCodes.put("420684","湖北省襄樊市宜城市");
        cityCodes.put("420700","湖北省鄂州市");
        cityCodes.put("420701","湖北省鄂州市市辖区");
        cityCodes.put("420702","湖北省鄂州市梁子湖区");
        cityCodes.put("420703","湖北省鄂州市华容区");
        cityCodes.put("420704","湖北省鄂州市鄂城区");
        cityCodes.put("420800","湖北省荆门市");
        cityCodes.put("420801","湖北省荆门市市辖区");
        cityCodes.put("420802","湖北省荆门市东宝区");
        cityCodes.put("420821","湖北省荆门市京山县");
        cityCodes.put("420822","湖北省荆门市沙洋县");
        cityCodes.put("420881","湖北省荆门市钟祥市");
        cityCodes.put("420900","湖北省孝感市");
        cityCodes.put("420901","湖北省孝感市市辖区");
        cityCodes.put("420902","湖北省孝感市孝南区");
        cityCodes.put("420921","湖北省孝感市孝昌县");
        cityCodes.put("420922","湖北省孝感市大悟县");
        cityCodes.put("420923","湖北省孝感市云梦县");
        cityCodes.put("420981","湖北省孝感市应城市");
        cityCodes.put("420982","湖北省孝感市安陆市");
        cityCodes.put("420983","湖北省孝感市广水市");
        cityCodes.put("420984","湖北省孝感市汉川市");
        cityCodes.put("421000","湖北省荆州市");
        cityCodes.put("421001","湖北省荆州市市辖区");
        cityCodes.put("421002","湖北省荆州市沙市区");
        cityCodes.put("421003","湖北省荆州市荆州区");
        cityCodes.put("421022","湖北省荆州市公安县");
        cityCodes.put("421023","湖北省荆州市监利县");
        cityCodes.put("421024","湖北省荆州市江陵县");
        cityCodes.put("421081","湖北省荆州市石首市");
        cityCodes.put("421083","湖北省荆州市洪湖市");
        cityCodes.put("421087","湖北省荆州市松滋市");
        cityCodes.put("421100","湖北省黄冈市");
        cityCodes.put("421101","湖北省黄冈市市辖区");
        cityCodes.put("421102","湖北省黄冈市黄州区");
        cityCodes.put("421121","湖北省黄冈市团风县");
        cityCodes.put("421122","湖北省黄冈市红安县");
        cityCodes.put("421123","湖北省黄冈市罗田县");
        cityCodes.put("421124","湖北省黄冈市英山县");
        cityCodes.put("421125","湖北省黄冈市浠水县");
        cityCodes.put("421126","湖北省黄冈市蕲春县");
        cityCodes.put("421127","湖北省黄冈市黄梅县");
        cityCodes.put("421181","湖北省黄冈市麻城市");
        cityCodes.put("421182","湖北省黄冈市武穴市");
        cityCodes.put("421200","湖北省咸宁市");
        cityCodes.put("421201","湖北省咸宁市市辖区");
        cityCodes.put("421202","湖北省咸宁市咸安区");
        cityCodes.put("421221","湖北省咸宁市嘉鱼县");
        cityCodes.put("421222","湖北省咸宁市通城县");
        cityCodes.put("421223","湖北省咸宁市崇阳县");
        cityCodes.put("421224","湖北省咸宁市通山县");
        cityCodes.put("422800","湖北省施土家族苗族自治州");
        cityCodes.put("422801","湖北省恩施土家族苗族自治州恩施县");
        cityCodes.put("422802","湖北省恩施土家族苗族自治州利川市");
        cityCodes.put("422822","湖北省恩施土家族苗族自治州建始县");
        cityCodes.put("422823","湖北省恩施土家族苗族自治州巴东县");
        cityCodes.put("422825","湖北省恩施土家族苗族自治州宣恩县");
        cityCodes.put("422826","湖北省恩施土家族苗族自治州咸丰县");
        cityCodes.put("422827","湖北省恩施土家族苗族自治州来凤县");
        cityCodes.put("422828","湖北省恩施土家族苗族自治州鹤峰县");
        cityCodes.put("429000","湖北省省直辖县级行政单位");
        cityCodes.put("429001","湖北省随州市");
        cityCodes.put("429004","湖北省仙桃市");
        cityCodes.put("429005","湖北省潜江市");
        cityCodes.put("429006","湖北省天门市");
        cityCodes.put("429021","湖北省神农架林区");
        cityCodes.put("430000","湖南省");
        cityCodes.put("430100","湖南省长沙市");
        cityCodes.put("430101","湖南省长沙市市辖区");
        cityCodes.put("430102","湖南省长沙市芙蓉区");
        cityCodes.put("430103","湖南省长沙市天心区");
        cityCodes.put("430104","湖南省长沙市岳麓区");
        cityCodes.put("430105","湖南省长沙市开福区");
        cityCodes.put("430111","湖南省长沙市雨花区");
        cityCodes.put("430121","湖南省长沙市长沙县");
        cityCodes.put("430122","湖南省长沙市望城县");
        cityCodes.put("430124","湖南省长沙市宁乡县");
        cityCodes.put("430181","湖南省长沙市浏阳市");
        cityCodes.put("430200","湖南省株洲市");
        cityCodes.put("430201","湖南省株洲市市辖区");
        cityCodes.put("430202","湖南省株洲市荷塘区");
        cityCodes.put("430203","湖南省株洲市芦淞区");
        cityCodes.put("430204","湖南省株洲市石峰区");
        cityCodes.put("430211","湖南省株洲市天元区");
        cityCodes.put("430221","湖南省株洲市株洲县");
        cityCodes.put("430223","湖南省株洲市攸县");
        cityCodes.put("430224","湖南省株洲市茶陵县");
        cityCodes.put("430225","湖南省株洲市炎陵县");
        cityCodes.put("430281","湖南省株洲市醴陵市");
        cityCodes.put("430300","湖南省湘潭市");
        cityCodes.put("430301","湖南省湘潭市市辖区");
        cityCodes.put("430302","湖南省湘潭市雨湖区");
        cityCodes.put("430304","湖南省湘潭市岳塘区");
        cityCodes.put("430321","湖南省湘潭市湘潭县");
        cityCodes.put("430381","湖南省湘潭市湘乡市");
        cityCodes.put("430382","湖南省湘潭市韶山市");
        cityCodes.put("430400","湖南省衡阳市");
        cityCodes.put("430401","湖南省衡阳市市辖区");
        cityCodes.put("430402","湖南省衡阳市江东区");
        cityCodes.put("430403","湖南省衡阳市城南区");
        cityCodes.put("430404","湖南省衡阳市城北区");
        cityCodes.put("430411","湖南省衡阳市郊区");
        cityCodes.put("430412","湖南省衡阳市南岳区");
        cityCodes.put("430421","湖南省衡阳市衡阳县");
        cityCodes.put("430422","湖南省衡阳市衡南县");
        cityCodes.put("430423","湖南省衡阳市衡山县");
        cityCodes.put("430424","湖南省衡阳市衡东县");
        cityCodes.put("430426","湖南省衡阳市祁东县");
        cityCodes.put("430481","湖南省衡阳市耒阳市");
        cityCodes.put("430482","湖南省衡阳市常宁市");
        cityCodes.put("430500","湖南省邵阳市");
        cityCodes.put("430501","湖南省邵阳市市辖区");
        cityCodes.put("430502","湖南省邵阳市双清区");
        cityCodes.put("430503","湖南省邵阳市大祥区");
        cityCodes.put("430511","湖南省邵阳市北塔区");
        cityCodes.put("430521","湖南省邵阳市邵东县");
        cityCodes.put("430522","湖南省邵阳市新邵县");
        cityCodes.put("430523","湖南省邵阳市邵阳县");
        cityCodes.put("430524","湖南省邵阳市隆回县");
        cityCodes.put("430525","湖南省邵阳市洞口县");
        cityCodes.put("430527","湖南省邵阳市绥宁县");
        cityCodes.put("430528","湖南省邵阳市新宁县");
        cityCodes.put("430529","湖南省邵阳市城步苗族自治县");
        cityCodes.put("430581","湖南省邵阳市武冈市");
        cityCodes.put("430600","湖南省岳阳市");
        cityCodes.put("430601","湖南省岳阳市市辖区");
        cityCodes.put("430602","湖南省岳阳市岳阳楼区");
        cityCodes.put("430603","湖南省岳阳市云溪区");
        cityCodes.put("430611","湖南省岳阳市君山区");
        cityCodes.put("430621","湖南省岳阳市岳阳县");
        cityCodes.put("430623","湖南省岳阳市华容县");
        cityCodes.put("430624","湖南省岳阳市湘阴县");
        cityCodes.put("430626","湖南省岳阳市平江县");
        cityCodes.put("430681","湖南省岳阳市汨罗市");
        cityCodes.put("430682","湖南省岳阳市临湘市");
        cityCodes.put("430700","湖南省常德市");
        cityCodes.put("430701","湖南省常德市市辖区");
        cityCodes.put("430702","湖南省常德市武陵区");
        cityCodes.put("430703","湖南省常德市鼎城区");
        cityCodes.put("430721","湖南省常德市安乡县");
        cityCodes.put("430722","湖南省常德市汉寿县");
        cityCodes.put("430723","湖南省常德市澧县");
        cityCodes.put("430724","湖南省常德市临澧县");
        cityCodes.put("430725","湖南省常德市桃源县");
        cityCodes.put("430726","湖南省常德市石门县");
        cityCodes.put("430781","湖南省常德市津市市");
        cityCodes.put("430800","湖南省张家界市");
        cityCodes.put("430801","湖南省张家界市市辖区");
        cityCodes.put("430802","湖南省张家界市永定区");
        cityCodes.put("430811","湖南省张家界市武陵源区");
        cityCodes.put("430821","湖南省张家界市慈利县");
        cityCodes.put("430822","湖南省张家界市桑植县");
        cityCodes.put("430900","湖南省益阳市");
        cityCodes.put("430901","湖南省益阳市市辖区");
        cityCodes.put("430902","湖南省益阳市资阳区");
        cityCodes.put("430903","湖南省益阳市赫山区");
        cityCodes.put("430921","湖南省益阳市南县");
        cityCodes.put("430922","湖南省益阳市桃江县");
        cityCodes.put("430923","湖南省益阳市安化县");
        cityCodes.put("430981","湖南省益阳市沅江市");
        cityCodes.put("431000","湖南省郴州市");
        cityCodes.put("431001","湖南省郴州市市辖区");
        cityCodes.put("431002","湖南省郴州市北湖区");
        cityCodes.put("431003","湖南省郴州市苏仙区");
        cityCodes.put("431021","湖南省郴州市桂阳县");
        cityCodes.put("431022","湖南省郴州市宜章县");
        cityCodes.put("431023","湖南省郴州市永兴县");
        cityCodes.put("431024","湖南省郴州市嘉禾县");
        cityCodes.put("431025","湖南省郴州市临武县");
        cityCodes.put("431026","湖南省郴州市汝城县");
        cityCodes.put("431027","湖南省郴州市桂东县");
        cityCodes.put("431028","湖南省郴州市安仁县");
        cityCodes.put("431081","湖南省郴州市资兴市");
        cityCodes.put("431100","湖南省永州市");
        cityCodes.put("431101","湖南省永州市市辖区");
        cityCodes.put("431102","湖南省永州市芝山区");
        cityCodes.put("431103","湖南省永州市冷水滩区");
        cityCodes.put("431121","湖南省永州市祁阳县");
        cityCodes.put("431122","湖南省永州市东安县");
        cityCodes.put("431123","湖南省永州市双牌县");
        cityCodes.put("431124","湖南省永州市道县");
        cityCodes.put("431125","湖南省永州市江永县");
        cityCodes.put("431126","湖南省永州市宁远县");
        cityCodes.put("431127","湖南省永州市蓝山县");
        cityCodes.put("431128","湖南省永州市新田县");
        cityCodes.put("431129","湖南省永州市江华瑶族自治县");
        cityCodes.put("431200","湖南省怀化市");
        cityCodes.put("431201","湖南省怀化市市辖区");
        cityCodes.put("431202","湖南省怀化市鹤城区");
        cityCodes.put("431221","湖南省怀化市中方县");
        cityCodes.put("431222","湖南省怀化市沅陵县");
        cityCodes.put("431223","湖南省怀化市辰溪县");
        cityCodes.put("431224","湖南省怀化市溆浦县");
        cityCodes.put("431225","湖南省怀化市会同县");
        cityCodes.put("431226","湖南省怀化市麻阳苗族自治县");
        cityCodes.put("431227","湖南省怀化市新晃侗族自治县");
        cityCodes.put("431228","湖南省怀化市芷江侗族自治县");
        cityCodes.put("431229","湖南省怀化市靖州苗族侗族自治县");
        cityCodes.put("431230","湖南省怀化市通道侗族自治县");
        cityCodes.put("431281","湖南省怀化市洪江市");
        cityCodes.put("432500","湖南省娄底地区");
        cityCodes.put("432501","湖南省娄底地区娄底市");
        cityCodes.put("432502","湖南省娄底地区冷水江市");
        cityCodes.put("432503","湖南省娄底地区涟源市");
        cityCodes.put("432522","湖南省娄底地区双峰县");
        cityCodes.put("432524","湖南省娄底地区新化县");
        cityCodes.put("433000","湖南省怀化市");
        cityCodes.put("433001","湖南省怀化市");
        cityCodes.put("433100","湖南省湘西土家族苗族自治州");
        cityCodes.put("433101","湖南省湘西土家族苗族自治州吉首市");
        cityCodes.put("433122","湖南省湘西土家族苗族自治州泸溪县");
        cityCodes.put("433123","湖南省湘西土家族苗族自治州凤凰县");
        cityCodes.put("433124","湖南省湘西土家族苗族自治州花垣县");
        cityCodes.put("433125","湖南省湘西土家族苗族自治州保靖县");
        cityCodes.put("433126","湖南省湘西土家族苗族自治州古丈县");
        cityCodes.put("433127","湖南省湘西土家族苗族自治州永顺县");
        cityCodes.put("433130","湖南省湘西土家族苗族自治州龙山县");
        cityCodes.put("440000","广东省");
        cityCodes.put("440100","广东省广州市");
        cityCodes.put("440101","广东省广州市市辖区");
        cityCodes.put("440102","广东省广州市东山区");
        cityCodes.put("440103","广东省广州市荔湾区");
        cityCodes.put("440104","广东省广州市越秀区");
        cityCodes.put("440105","广东省广州市海珠区");
        cityCodes.put("440106","广东省广州市天河区");
        cityCodes.put("440107","广东省广州市芳村区");
        cityCodes.put("440111","广东省广州市白云区");
        cityCodes.put("440112","广东省广州市黄埔区");
        cityCodes.put("440181","广东省广州市番禺市");
        cityCodes.put("440182","广东省广州市花都市");
        cityCodes.put("440183","广东省广州市增城市");
        cityCodes.put("440184","广东省广州市从化市");
        cityCodes.put("440200","广东省韶关市");
        cityCodes.put("440201","广东省韶关市市辖区");
        cityCodes.put("440202","广东省韶关市北江区");
        cityCodes.put("440203","广东省韶关市武江区");
        cityCodes.put("440204","广东省韶关市浈江区");
        cityCodes.put("440221","广东省韶关市曲江县");
        cityCodes.put("440222","广东省韶关市始兴县");
        cityCodes.put("440224","广东省韶关市仁化县");
        cityCodes.put("440229","广东省韶关市翁源县");
        cityCodes.put("440232","广东省韶关市乳源瑶族自治县");
        cityCodes.put("440233","广东省韶关市新丰县");
        cityCodes.put("440281","广东省韶关市乐昌市");
        cityCodes.put("440282","广东省韶关市南雄市");
        cityCodes.put("440300","广东省深圳市");
        cityCodes.put("440301","广东省深圳市市辖区");
        cityCodes.put("440303","广东省深圳市罗湖区");
        cityCodes.put("440304","广东省深圳市福田区");
        cityCodes.put("440305","广东省深圳市南山区");
        cityCodes.put("440306","广东省深圳市宝安区");
        cityCodes.put("440307","广东省深圳市龙岗区");
        cityCodes.put("440308","广东省深圳市盐田区");
        cityCodes.put("440400","广东省珠海市");
        cityCodes.put("440401","广东省珠海市市辖区");
        cityCodes.put("440402","广东省珠海市香洲区");
        cityCodes.put("440421","广东省珠海市斗门县");
        cityCodes.put("440500","广东省汕头市");
        cityCodes.put("440501","广东省汕头市市辖区");
        cityCodes.put("440506","广东省汕头市达濠区");
        cityCodes.put("440507","广东省汕头市龙湖区");
        cityCodes.put("440508","广东省汕头市金园区");
        cityCodes.put("440509","广东省汕头市升平区");
        cityCodes.put("440510","广东省汕头市河浦区");
        cityCodes.put("440523","广东省汕头市南澳县");
        cityCodes.put("440582","广东省汕头市潮阳市");
        cityCodes.put("440583","广东省汕头市澄海市");
        cityCodes.put("440600","广东省佛山市");
        cityCodes.put("440601","广东省佛山市市辖区");
        cityCodes.put("440602","广东省佛山市城区");
        cityCodes.put("440603","广东省佛山市石湾区");
        cityCodes.put("440681","广东省佛山市顺德市");
        cityCodes.put("440682","广东省佛山市南海市");
        cityCodes.put("440683","广东省佛山市三水市");
        cityCodes.put("440684","广东省佛山市高明市");
        cityCodes.put("440700","广东省江门市");
        cityCodes.put("440701","广东省江门市市辖区");
        cityCodes.put("440703","广东省江门市蓬江区");
        cityCodes.put("440704","广东省江门市江海区");
        cityCodes.put("440781","广东省江门市台山市");
        cityCodes.put("440782","广东省江门市新会市");
        cityCodes.put("440783","广东省江门市开平市");
        cityCodes.put("440784","广东省江门市鹤山市");
        cityCodes.put("440785","广东省江门市恩平市");
        cityCodes.put("440800","广东省湛江市");
        cityCodes.put("440801","广东省湛江市市辖区");
        cityCodes.put("440802","广东省湛江市赤坎区");
        cityCodes.put("440803","广东省湛江市霞山区");
        cityCodes.put("440804","广东省湛江市坡头区");
        cityCodes.put("440811","广东省湛江市麻章区");
        cityCodes.put("440823","广东省湛江市遂溪县");
        cityCodes.put("440825","广东省湛江市徐闻县");
        cityCodes.put("440881","广东省湛江市廉江市");
        cityCodes.put("440882","广东省湛江市雷州市");
        cityCodes.put("440883","广东省湛江市吴川市");
        cityCodes.put("440900","广东省茂名市");
        cityCodes.put("440901","广东省茂名市市辖区");
        cityCodes.put("440902","广东省茂名市茂南区");
        cityCodes.put("440923","广东省茂名市电白县");
        cityCodes.put("440981","广东省茂名市高州市");
        cityCodes.put("440982","广东省茂名市化州市");
        cityCodes.put("440983","广东省茂名市信宜市");
        cityCodes.put("441200","广东省肇庆市");
        cityCodes.put("441201","广东省肇庆市市辖区");
        cityCodes.put("441202","广东省肇庆市端州区");
        cityCodes.put("441203","广东省肇庆市鼎湖区");
        cityCodes.put("441223","广东省肇庆市广宁县");
        cityCodes.put("441224","广东省肇庆市怀集县");
        cityCodes.put("441225","广东省肇庆市封开县");
        cityCodes.put("441226","广东省肇庆市德庆县");
        cityCodes.put("441283","广东省肇庆市高要市");
        cityCodes.put("441284","广东省肇庆市四会市");
        cityCodes.put("441300","广东省惠州市");
        cityCodes.put("441301","广东省惠州市市辖区");
        cityCodes.put("441302","广东省惠州市惠城区");
        cityCodes.put("441322","广东省惠州市博罗县");
        cityCodes.put("441323","广东省惠州市惠东县");
        cityCodes.put("441324","广东省惠州市龙门县");
        cityCodes.put("441381","广东省惠州市惠阳市");
        cityCodes.put("441400","广东省梅州市");
        cityCodes.put("441401","广东省梅州市市辖区");
        cityCodes.put("441402","广东省梅州市梅江区");
        cityCodes.put("441421","广东省梅州市梅县");
        cityCodes.put("441422","广东省梅州市大埔县");
        cityCodes.put("441423","广东省梅州市丰顺县");
        cityCodes.put("441424","广东省梅州市五华县");
        cityCodes.put("441426","广东省梅州市平远县");
        cityCodes.put("441427","广东省梅州市蕉岭县");
        cityCodes.put("441481","广东省梅州市兴宁市");
        cityCodes.put("441500","广东省汕尾市");
        cityCodes.put("441501","广东省汕尾市市辖区");
        cityCodes.put("441502","广东省汕尾市城区");
        cityCodes.put("441521","广东省汕尾市海丰县");
        cityCodes.put("441523","广东省汕尾市陆河县");
        cityCodes.put("441581","广东省汕尾市陆丰市");
        cityCodes.put("441600","广东省河源市");
        cityCodes.put("441601","广东省河源市市辖区");
        cityCodes.put("441602","广东省河源市源城区");
        cityCodes.put("441621","广东省河源市紫金县");
        cityCodes.put("441622","广东省河源市龙川县");
        cityCodes.put("441623","广东省河源市连平县");
        cityCodes.put("441624","广东省河源市和平县");
        cityCodes.put("441625","广东省河源市东源县");
        cityCodes.put("441700","广东省阳江市");
        cityCodes.put("441701","广东省阳江市市辖区");
        cityCodes.put("441702","广东省阳江市江城区");
        cityCodes.put("441721","广东省阳江市阳西县");
        cityCodes.put("441723","广东省阳江市阳东县");
        cityCodes.put("441781","广东省阳江市阳春市");
        cityCodes.put("441800","广东省清远市");
        cityCodes.put("441801","广东省清远市市辖区");
        cityCodes.put("441802","广东省清远市清城区");
        cityCodes.put("441821","广东省清远市佛冈县");
        cityCodes.put("441823","广东省清远市阳山县");
        cityCodes.put("441825","广东省清远市连山壮族瑶族自治县");
        cityCodes.put("441826","广东省清远市连南瑶族自治县");
        cityCodes.put("441827","广东省清远市清新县");
        cityCodes.put("441881","广东省清远市英德市");
        cityCodes.put("441882","广东省清远市连州市");
        cityCodes.put("441900","广东省东莞市");
        cityCodes.put("441901","广东省东莞市市辖区");
        cityCodes.put("442000","广东省中山市");
        cityCodes.put("442001","广东省中山市市辖区");
        cityCodes.put("445100","广东省潮州市");
        cityCodes.put("445101","广东省潮州市市辖区");
        cityCodes.put("445102","广东省潮州市湘桥区");
        cityCodes.put("445121","广东省潮州市潮安县");
        cityCodes.put("445122","广东省潮州市饶平县");
        cityCodes.put("445200","广东省揭阳市");
        cityCodes.put("445201","广东省揭阳市市辖区");
        cityCodes.put("445202","广东省揭阳市榕城区");
        cityCodes.put("445221","广东省揭阳市揭东县");
        cityCodes.put("445222","广东省揭阳市揭西县");
        cityCodes.put("445224","广东省揭阳市惠来县");
        cityCodes.put("445281","广东省揭阳市普宁市");
        cityCodes.put("445300","广东省云浮市");
        cityCodes.put("445301","广东省云浮市市辖区");
        cityCodes.put("445302","广东省云浮市云城区");
        cityCodes.put("445321","广东省云浮市新兴县");
        cityCodes.put("445322","广东省云浮市郁南县");
        cityCodes.put("445323","广东省云浮市云安县");
        cityCodes.put("445381","广东省云浮市罗定市");
        cityCodes.put("450000","广西壮族自治区");
        cityCodes.put("450100","广西壮族自治区南宁市");
        cityCodes.put("450101","广西壮族自治区南宁市市辖区");
        cityCodes.put("450102","广西壮族自治区南宁市兴宁区");
        cityCodes.put("450103","广西壮族自治区南宁市新城区");
        cityCodes.put("450104","广西壮族自治区南宁市城北区");
        cityCodes.put("450105","广西壮族自治区南宁市江南区");
        cityCodes.put("450106","广西壮族自治区南宁市永新区");
        cityCodes.put("450111","广西壮族自治区南宁市市郊区");
        cityCodes.put("450121","广西壮族自治区南宁市邕宁县");
        cityCodes.put("450122","广西壮族自治区南宁市武鸣县");
        cityCodes.put("450200","广西壮族自治区柳州市");
        cityCodes.put("450201","广西壮族自治区柳州市市辖区");
        cityCodes.put("450202","广西壮族自治区柳州市城中区");
        cityCodes.put("450203","广西壮族自治区柳州市鱼峰区");
        cityCodes.put("450204","广西壮族自治区柳州市柳南区");
        cityCodes.put("450205","广西壮族自治区柳州市柳北区");
        cityCodes.put("450211","广西壮族自治区柳州市市郊区");
        cityCodes.put("450221","广西壮族自治区柳州市柳江县");
        cityCodes.put("450222","广西壮族自治区柳州市柳城县");
        cityCodes.put("450300","广西壮族自治区桂林市");
        cityCodes.put("450301","广西壮族自治区桂林市市辖区");
        cityCodes.put("450302","广西壮族自治区桂林市秀峰区");
        cityCodes.put("450303","广西壮族自治区桂林市叠彩区");
        cityCodes.put("450304","广西壮族自治区桂林市象山区");
        cityCodes.put("450305","广西壮族自治区桂林市七星区");
        cityCodes.put("450311","广西壮族自治区桂林市雁山区");
        cityCodes.put("450321","广西壮族自治区桂林市阳朔县");
        cityCodes.put("450322","广西壮族自治区桂林市临桂县");
        cityCodes.put("450323","广西壮族自治区桂林市灵川县");
        cityCodes.put("450324","广西壮族自治区桂林市全州县");
        cityCodes.put("450325","广西壮族自治区桂林市兴安县");
        cityCodes.put("450326","广西壮族自治区桂林市永福县");
        cityCodes.put("450327","广西壮族自治区桂林市灌阳县");
        cityCodes.put("450328","广西壮族自治区桂林市龙胜各族自治县");
        cityCodes.put("450329","广西壮族自治区桂林市资源县");
        cityCodes.put("450330","广西壮族自治区桂林市平乐县");
        cityCodes.put("450331","广西壮族自治区桂林市荔浦县");
        cityCodes.put("450332","广西壮族自治区桂林市恭城瑶族自治县");
        cityCodes.put("450400","广西壮族自治区梧州市");
        cityCodes.put("450401","广西壮族自治区梧州市市辖区");
        cityCodes.put("450403","广西壮族自治区梧州市万秀区");
        cityCodes.put("450404","广西壮族自治区梧州市蝶山区");
        cityCodes.put("450411","广西壮族自治区梧州市市郊区");
        cityCodes.put("450421","广西壮族自治区梧州市苍梧县");
        cityCodes.put("450422","广西壮族自治区梧州市藤县");
        cityCodes.put("450423","广西壮族自治区梧州市蒙山县");
        cityCodes.put("450481","广西壮族自治区梧州市岑溪市");
        cityCodes.put("450500","广西壮族自治区北海市");
        cityCodes.put("450501","广西壮族自治区北海市市辖区");
        cityCodes.put("450502","广西壮族自治区北海市海城区");
        cityCodes.put("450503","广西壮族自治区北海市银海区");
        cityCodes.put("450512","广西壮族自治区北海市铁山港区");
        cityCodes.put("450521","广西壮族自治区北海市合浦县");
        cityCodes.put("450600","广西壮族自治区防城港市");
        cityCodes.put("450601","广西壮族自治区防城港市市辖区");
        cityCodes.put("450602","广西壮族自治区防城港市港口区");
        cityCodes.put("450603","广西壮族自治区防城港市防城区");
        cityCodes.put("450621","广西壮族自治区防城港市上思县");
        cityCodes.put("450681","广西壮族自治区防城港市东兴市");
        cityCodes.put("450700","广西壮族自治区钦州市");
        cityCodes.put("450701","广西壮族自治区钦州市市辖区");
        cityCodes.put("450702","广西壮族自治区钦州市钦南区");
        cityCodes.put("450703","广西壮族自治区钦州市钦北区");
        cityCodes.put("450721","广西壮族自治区钦州市灵山县");
        cityCodes.put("450722","广西壮族自治区钦州市浦北县");
        cityCodes.put("450800","广西壮族自治区贵港市");
        cityCodes.put("450801","广西壮族自治区贵港市市辖区");
        cityCodes.put("450802","广西壮族自治区贵港市港北区");
        cityCodes.put("450803","广西壮族自治区贵港市港南区");
        cityCodes.put("450821","广西壮族自治区贵港市平南县");
        cityCodes.put("450881","广西壮族自治区贵港市桂平市");
        cityCodes.put("450900","广西壮族自治区玉林市");
        cityCodes.put("450901","广西壮族自治区玉林市市辖区");
        cityCodes.put("450902","广西壮族自治区玉林市玉州区");
        cityCodes.put("450921","广西壮族自治区玉林市容县");
        cityCodes.put("450922","广西壮族自治区玉林市陆川县");
        cityCodes.put("450923","广西壮族自治区玉林市博白县");
        cityCodes.put("450924","广西壮族自治区玉林市兴业县");
        cityCodes.put("450981","广西壮族自治区玉林市北流市");
        cityCodes.put("452100","广西壮族自治区南宁地区");
        cityCodes.put("452101","广西壮族自治区南宁地区凭祥市");
        cityCodes.put("452122","广西壮族自治区南宁地区横县");
        cityCodes.put("452123","广西壮族自治区南宁地区宾阳县");
        cityCodes.put("452124","广西壮族自治区南宁地区上林县");
        cityCodes.put("452126","广西壮族自治区南宁地区隆安县");
        cityCodes.put("452127","广西壮族自治区南宁地区马山县");
        cityCodes.put("452128","广西壮族自治区南宁地区扶绥县");
        cityCodes.put("452129","广西壮族自治区南宁地区崇左县");
        cityCodes.put("452130","广西壮族自治区南宁地区大新县");
        cityCodes.put("452131","广西壮族自治区南宁地区天等县");
        cityCodes.put("452132","广西壮族自治区南宁地区宁明县");
        cityCodes.put("452133","广西壮族自治区南宁地区龙州县");
        cityCodes.put("452200","广西壮族自治区柳州地区");
        cityCodes.put("452201","广西壮族自治区柳州地区合山市");
        cityCodes.put("452223","广西壮族自治区柳州地区鹿寨县");
        cityCodes.put("452224","广西壮族自治区柳州地区象州县");
        cityCodes.put("452225","广西壮族自治区柳州地区武宣县");
        cityCodes.put("452226","广西壮族自治区柳州地区来宾县");
        cityCodes.put("452227","广西壮族自治区柳州地区融安县");
        cityCodes.put("452228","广西壮族自治区柳州地区三江侗族自治县");
        cityCodes.put("452229","广西壮族自治区柳州地区融水苗族自治县");
        cityCodes.put("452230","广西壮族自治区柳州地区金秀瑶族自治县");
        cityCodes.put("452231","广西壮族自治区柳州地区忻城县");
        cityCodes.put("452400","广西壮族自治区贺州地区");
        cityCodes.put("452402","广西壮族自治区贺州地区贺州市");
        cityCodes.put("452424","广西壮族自治区贺州地区昭平县");
        cityCodes.put("452427","广西壮族自治区贺州地区钟山县");
        cityCodes.put("452428","广西壮族自治区贺州地区富川瑶族自治县");
        cityCodes.put("452600","广西壮族自治区百色地区");
        cityCodes.put("452601","广西壮族自治区百色地区百色市");
        cityCodes.put("452622","广西壮族自治区百色地区田阳县");
        cityCodes.put("452623","广西壮族自治区百色地区田东县");
        cityCodes.put("452624","广西壮族自治区百色地区平果县");
        cityCodes.put("452625","广西壮族自治区百色地区德保县");
        cityCodes.put("452626","广西壮族自治区百色地区靖西县");
        cityCodes.put("452627","广西壮族自治区百色地区那坡县");
        cityCodes.put("452628","广西壮族自治区百色地区凌云县");
        cityCodes.put("452629","广西壮族自治区百色地区乐业县");
        cityCodes.put("452630","广西壮族自治区百色地区田林县");
        cityCodes.put("452631","广西壮族自治区百色地区隆林各族自治县");
        cityCodes.put("452632","广西壮族自治区百色地区西林县");
        cityCodes.put("452700","广西壮族自治区河池地区");
        cityCodes.put("452701","广西壮族自治区河池地区河池市");
        cityCodes.put("452702","广西壮族自治区河池地区宜州市");
        cityCodes.put("452723","广西壮族自治区河池地区罗城仫佬族自治县");
        cityCodes.put("452724","广西壮族自治区河池地区环江毛南族自治县");
        cityCodes.put("452725","广西壮族自治区河池地区南丹县");
        cityCodes.put("452726","广西壮族自治区河池地区天峨县");
        cityCodes.put("452727","广西壮族自治区河池地区凤山县");
        cityCodes.put("452728","广西壮族自治区河池地区东兰县");
        cityCodes.put("452729","广西壮族自治区河池地区巴马瑶族自治县");
        cityCodes.put("452730","广西壮族自治区河池地区都安瑶族自治县");
        cityCodes.put("452731","广西壮族自治区河池地区大化瑶族自治县");
        cityCodes.put("460000","海南省");
        cityCodes.put("460001","海南省三亚市通什市");
        cityCodes.put("460002","海南省三亚市琼海市");
        cityCodes.put("460003","海南省三亚市儋州市");
        cityCodes.put("460004","海南省三亚市琼山市");
        cityCodes.put("460005","海南省三亚市文昌市");
        cityCodes.put("460006","海南省三亚市万宁市");
        cityCodes.put("460007","海南省三亚市东方市");
        cityCodes.put("460025","海南省三亚市定安县");
        cityCodes.put("460026","海南省三亚市屯昌县");
        cityCodes.put("460027","海南省三亚市澄迈县");
        cityCodes.put("460028","海南省三亚市临高县");
        cityCodes.put("460030","海南省三亚市白沙黎族自治县");
        cityCodes.put("460031","海南省三亚市昌江黎族自治县");
        cityCodes.put("460033","海南省三亚市乐东黎族自治县");
        cityCodes.put("460034","海南省三亚市陵水黎族自治县");
        cityCodes.put("460035","海南省三亚市保亭黎族苗族自治县");
        cityCodes.put("460036","海南省三亚市琼中黎族苗族自治县");
        cityCodes.put("460037","海南省西沙群岛");
        cityCodes.put("460038","海南省南沙群岛");
        cityCodes.put("460039","海南省中沙群岛的岛礁及其海域");
        cityCodes.put("460100","海南省海口市");
        cityCodes.put("460101","海南省海口市市辖区");
        cityCodes.put("460102","海南省海口市振东区");
        cityCodes.put("460103","海南省海口市新华区");
        cityCodes.put("460104","海南省海口市秀英区");
        cityCodes.put("460200","海南省三亚市");
        cityCodes.put("460201","海南省三亚市市辖区");
        cityCodes.put("500000","重庆市");
        cityCodes.put("500100","重庆市市辖区");
        cityCodes.put("500101","重庆市万州区");
        cityCodes.put("500102","重庆市涪陵区");
        cityCodes.put("500103","重庆市渝中区");
        cityCodes.put("500104","重庆市大渡口区");
        cityCodes.put("500105","重庆市江北区");
        cityCodes.put("500106","重庆市沙坪坝区");
        cityCodes.put("500107","重庆市九龙坡区");
        cityCodes.put("500108","重庆市南岸区");
        cityCodes.put("500109","重庆市北碚区");
        cityCodes.put("500110","重庆市万盛区");
        cityCodes.put("500111","重庆市双桥区");
        cityCodes.put("500112","重庆市渝北区");
        cityCodes.put("500113","重庆市巴南区");
        cityCodes.put("500200","重庆市县");
        cityCodes.put("500221","重庆市长寿县");
        cityCodes.put("500222","重庆市綦江县");
        cityCodes.put("500223","重庆市潼南县");
        cityCodes.put("500224","重庆市铜梁县");
        cityCodes.put("500225","重庆市大足县");
        cityCodes.put("500226","重庆市荣昌县");
        cityCodes.put("500227","重庆市璧山县");
        cityCodes.put("500228","重庆市梁平县");
        cityCodes.put("500229","重庆市城口县");
        cityCodes.put("500230","重庆市丰都县");
        cityCodes.put("500231","重庆市垫江县");
        cityCodes.put("500232","重庆市武隆县");
        cityCodes.put("500233","重庆市忠县");
        cityCodes.put("500234","重庆市开县");
        cityCodes.put("500235","重庆市云阳县");
        cityCodes.put("500236","重庆市奉节县");
        cityCodes.put("500237","重庆市巫山县");
        cityCodes.put("500238","重庆市巫溪县");
        cityCodes.put("500239","重庆市黔江土家族苗族自治县");
        cityCodes.put("500240","重庆市石柱土家族自治县");
        cityCodes.put("500241","重庆市秀山土家族苗族自治县");
        cityCodes.put("500242","重庆市酉阳土家族苗族自治县");
        cityCodes.put("500243","重庆市彭水苗族土家族自治县");
        cityCodes.put("500300","重庆市(市)");
        cityCodes.put("500381","重庆市江津市");
        cityCodes.put("500382","重庆市合川市");
        cityCodes.put("500383","重庆市永川市");
        cityCodes.put("500384","重庆市南川市");
        cityCodes.put("510000","四川省");
        cityCodes.put("510100","四川省成都市");
        cityCodes.put("510101","四川省成都市市辖区");
        cityCodes.put("510104","四川省成都市锦江区");
        cityCodes.put("510105","四川省成都市青羊区");
        cityCodes.put("510106","四川省成都市金牛区");
        cityCodes.put("510107","四川省成都市武侯区");
        cityCodes.put("510108","四川省成都市成华区");
        cityCodes.put("510112","四川省成都市龙泉驿区");
        cityCodes.put("510113","四川省成都市青白江区");
        cityCodes.put("510121","四川省成都市金堂县");
        cityCodes.put("510122","四川省成都市双流县");
        cityCodes.put("510123","四川省成都市温江县");
        cityCodes.put("510124","四川省成都市郫县");
        cityCodes.put("510125","四川省成都市新都县");
        cityCodes.put("510129","四川省成都市大邑县");
        cityCodes.put("510131","四川省成都市蒲江县");
        cityCodes.put("510132","四川省成都市新津县");
        cityCodes.put("510181","四川省成都市都江堰市");
        cityCodes.put("510182","四川省成都市彭州市");
        cityCodes.put("510183","四川省成都市邛崃市");
        cityCodes.put("510184","四川省成都市崇州市");
        cityCodes.put("510300","四川省自贡市");
        cityCodes.put("510301","四川省自贡市市辖区");
        cityCodes.put("510302","四川省自贡市自流井区");
        cityCodes.put("510303","四川省自贡市贡井区");
        cityCodes.put("510304","四川省自贡市大安区");
        cityCodes.put("510311","四川省自贡市沿滩区");
        cityCodes.put("510321","四川省自贡市荣县");
        cityCodes.put("510322","四川省自贡市富顺县");
        cityCodes.put("510400","四川省攀枝花市");
        cityCodes.put("510401","四川省攀枝花市市辖区");
        cityCodes.put("510402","四川省攀枝花市东区");
        cityCodes.put("510403","四川省攀枝花市西区");
        cityCodes.put("510411","四川省攀枝花市仁和区");
        cityCodes.put("510421","四川省攀枝花市米易县");
        cityCodes.put("510422","四川省攀枝花市盐边县");
        cityCodes.put("510500","四川省泸州市");
        cityCodes.put("510501","四川省泸州市市辖区");
        cityCodes.put("510502","四川省泸州市江阳区");
        cityCodes.put("510503","四川省泸州市纳溪区");
        cityCodes.put("510504","四川省泸州市龙马潭区");
        cityCodes.put("510521","四川省泸州市泸县");
        cityCodes.put("510522","四川省泸州市合江县");
        cityCodes.put("510524","四川省泸州市叙永县");
        cityCodes.put("510525","四川省泸州市古蔺县");
        cityCodes.put("510600","四川省德阳市");
        cityCodes.put("510601","四川省德阳市市辖区");
        cityCodes.put("510603","四川省德阳市旌阳区");
        cityCodes.put("510623","四川省德阳市中江县");
        cityCodes.put("510626","四川省德阳市罗江县");
        cityCodes.put("510681","四川省德阳市广汉市");
        cityCodes.put("510682","四川省德阳市什邡市");
        cityCodes.put("510683","四川省德阳市绵竹市");
        cityCodes.put("510700","四川省绵阳市");
        cityCodes.put("510701","四川省绵阳市市辖区");
        cityCodes.put("510703","四川省绵阳市涪城区");
        cityCodes.put("510704","四川省绵阳市游仙区");
        cityCodes.put("510722","四川省绵阳市三台县");
        cityCodes.put("510723","四川省绵阳市盐亭县");
        cityCodes.put("510724","四川省绵阳市安县");
        cityCodes.put("510725","四川省绵阳市梓潼县");
        cityCodes.put("510726","四川省绵阳市北川县");
        cityCodes.put("510727","四川省绵阳市平武县");
        cityCodes.put("510781","四川省绵阳市江油市");
        cityCodes.put("510800","四川省广元市");
        cityCodes.put("510801","四川省广元市市辖区");
        cityCodes.put("510802","四川省广元市市中区");
        cityCodes.put("510811","四川省广元市元坝区");
        cityCodes.put("510812","四川省广元市朝天区");
        cityCodes.put("510821","四川省广元市旺苍县");
        cityCodes.put("510822","四川省广元市青川县");
        cityCodes.put("510823","四川省广元市剑阁县");
        cityCodes.put("510824","四川省广元市苍溪县");
        cityCodes.put("510900","四川省遂宁市");
        cityCodes.put("510901","四川省遂宁市市辖区");
        cityCodes.put("510902","四川省遂宁市市中区");
        cityCodes.put("510921","四川省遂宁市蓬溪县");
        cityCodes.put("510922","四川省遂宁市射洪县");
        cityCodes.put("510923","四川省遂宁市大英县");
        cityCodes.put("511000","四川省内江市");
        cityCodes.put("511001","四川省内江市市辖区");
        cityCodes.put("511002","四川省内江市市中区");
        cityCodes.put("511011","四川省内江市东兴区");
        cityCodes.put("511024","四川省内江市威远县");
        cityCodes.put("511025","四川省内江市资中县");
        cityCodes.put("511028","四川省内江市隆昌县");
        cityCodes.put("511100","四川省乐山市");
        cityCodes.put("511101","四川省乐山市市辖区");
        cityCodes.put("511102","四川省乐山市市中区");
        cityCodes.put("511111","四川省乐山市沙湾区");
        cityCodes.put("511112","四川省乐山市五通桥区");
        cityCodes.put("511113","四川省乐山市金口河区");
        cityCodes.put("511123","四川省乐山市犍为县");
        cityCodes.put("511124","四川省乐山市井研县");
        cityCodes.put("511126","四川省乐山市夹江县");
        cityCodes.put("511129","四川省乐山市沐川县");
        cityCodes.put("511132","四川省乐山市峨边彝族自治县");
        cityCodes.put("511133","四川省乐山市马边彝族自治县");
        cityCodes.put("511181","四川省乐山市峨眉山市");
        cityCodes.put("511300","四川省南充市");
        cityCodes.put("511301","四川省南充市市辖区");
        cityCodes.put("511302","四川省南充市顺庆区");
        cityCodes.put("511303","四川省南充市高坪区");
        cityCodes.put("511304","四川省南充市嘉陵区");
        cityCodes.put("511321","四川省南充市南部县");
        cityCodes.put("511322","四川省南充市营山县");
        cityCodes.put("511323","四川省南充市蓬安县");
        cityCodes.put("511324","四川省南充市仪陇县");
        cityCodes.put("511325","四川省南充市西充县");
        cityCodes.put("511381","四川省南充市阆中市");
        cityCodes.put("511500","四川省宜宾市");
        cityCodes.put("511501","四川省宜宾市市辖区");
        cityCodes.put("511502","四川省宜宾市翠屏区");
        cityCodes.put("511521","四川省宜宾市宜宾县");
        cityCodes.put("511522","四川省宜宾市南溪县");
        cityCodes.put("511523","四川省宜宾市江安县");
        cityCodes.put("511524","四川省宜宾市长宁县");
        cityCodes.put("511525","四川省宜宾市高县");
        cityCodes.put("511526","四川省宜宾市珙县");
        cityCodes.put("511527","四川省宜宾市筠连县");
        cityCodes.put("511528","四川省宜宾市兴文县");
        cityCodes.put("511529","四川省宜宾市屏山县");
        cityCodes.put("511600","四川省广安市");
        cityCodes.put("511601","四川省广安市市辖区");
        cityCodes.put("511602","四川省广安市广安区");
        cityCodes.put("511621","四川省广安市岳池县");
        cityCodes.put("511622","四川省广安市武胜县");
        cityCodes.put("511623","四川省广安市邻水县");
        cityCodes.put("511681","四川省广安市华蓥市");
        cityCodes.put("513000","四川省达川地区");
        cityCodes.put("513001","四川省达川地区达川市");
        cityCodes.put("513002","四川省达川地区万源市");
        cityCodes.put("513021","四川省达川地区达县");
        cityCodes.put("513022","四川省达川地区宣汉县");
        cityCodes.put("513023","四川省达川地区开江县");
        cityCodes.put("513029","四川省达川地区大竹县");
        cityCodes.put("513030","四川省达川地区渠县");
        cityCodes.put("513100","四川省雅安地区");
        cityCodes.put("513101","四川省雅安地区雅安市");
        cityCodes.put("513122","四川省雅安地区名山县");
        cityCodes.put("513123","四川省雅安地区荥经县");
        cityCodes.put("513124","四川省雅安地区汉源县");
        cityCodes.put("513125","四川省雅安地区石棉县");
        cityCodes.put("513126","四川省雅安地区天全县");
        cityCodes.put("513127","四川省雅安地区芦山县");
        cityCodes.put("513128","四川省雅安地区宝兴县");
        cityCodes.put("513200","四川省阿坝藏族羌族自治州");
        cityCodes.put("513221","四川省阿坝藏族羌族自治州汶川县");
        cityCodes.put("513222","四川省阿坝藏族羌族自治州理县");
        cityCodes.put("513223","四川省阿坝藏族羌族自治州茂县");
        cityCodes.put("513224","四川省阿坝藏族羌族自治州松潘县");
        cityCodes.put("513225","四川省阿坝藏族羌族自治州九寨沟县");
        cityCodes.put("513226","四川省阿坝藏族羌族自治州金川县");
        cityCodes.put("513227","四川省阿坝藏族羌族自治州小金县");
        cityCodes.put("513228","四川省阿坝藏族羌族自治州黑水县");
        cityCodes.put("513229","四川省阿坝藏族羌族自治州马尔康县");
        cityCodes.put("513230","四川省阿坝藏族羌族自治州壤塘县");
        cityCodes.put("513231","四川省阿坝藏族羌族自治州阿坝县");
        cityCodes.put("513232","四川省阿坝藏族羌族自治州若尔盖县");
        cityCodes.put("513233","四川省阿坝藏族羌族自治州红原县");
        cityCodes.put("513300","四川省甘孜藏族自治州");
        cityCodes.put("513321","四川省甘孜藏族自治州康定县");
        cityCodes.put("513322","四川省甘孜藏族自治州泸定县");
        cityCodes.put("513323","四川省甘孜藏族自治州丹巴县");
        cityCodes.put("513324","四川省甘孜藏族自治州九龙县");
        cityCodes.put("513325","四川省甘孜藏族自治州雅江县");
        cityCodes.put("513326","四川省甘孜藏族自治州道孚县");
        cityCodes.put("513327","四川省甘孜藏族自治州炉霍县");
        cityCodes.put("513328","四川省甘孜藏族自治州甘孜县");
        cityCodes.put("513329","四川省甘孜藏族自治州新龙县");
        cityCodes.put("513330","四川省甘孜藏族自治州德格县");
        cityCodes.put("513331","四川省甘孜藏族自治州白玉县");
        cityCodes.put("513332","四川省甘孜藏族自治州石渠县");
        cityCodes.put("513333","四川省甘孜藏族自治州色达县");
        cityCodes.put("513334","四川省甘孜藏族自治州理塘县");
        cityCodes.put("513335","四川省甘孜藏族自治州巴塘县");
        cityCodes.put("513336","四川省甘孜藏族自治州乡城县");
        cityCodes.put("513337","四川省甘孜藏族自治州稻城县");
        cityCodes.put("513338","四川省甘孜藏族自治州得荣县");
        cityCodes.put("513400","四川省凉山彝族自治州");
        cityCodes.put("513401","四川省凉山彝族自治州西昌市");
        cityCodes.put("513422","四川省凉山彝族自治州木里藏族自治县");
        cityCodes.put("513423","四川省凉山彝族自治州盐源县");
        cityCodes.put("513424","四川省凉山彝族自治州德昌县");
        cityCodes.put("513425","四川省凉山彝族自治州会理县");
        cityCodes.put("513426","四川省凉山彝族自治州会东县");
        cityCodes.put("513427","四川省凉山彝族自治州宁南县");
        cityCodes.put("513428","四川省凉山彝族自治州普格县");
        cityCodes.put("513429","四川省凉山彝族自治州布拖县");
        cityCodes.put("513430","四川省凉山彝族自治州金阳县");
        cityCodes.put("513431","四川省凉山彝族自治州昭觉县");
        cityCodes.put("513432","四川省凉山彝族自治州喜德县");
        cityCodes.put("513433","四川省凉山彝族自治州冕宁县");
        cityCodes.put("513434","四川省凉山彝族自治州越西县");
        cityCodes.put("513435","四川省凉山彝族自治州甘洛县");
        cityCodes.put("513436","四川省凉山彝族自治州美姑县");
        cityCodes.put("513437","四川省凉山彝族自治州雷波县");
        cityCodes.put("513700","四川省巴中地区");
        cityCodes.put("513701","四川省巴中地区巴中市");
        cityCodes.put("513721","四川省巴中地区通江县");
        cityCodes.put("513722","四川省巴中地区南江县");
        cityCodes.put("513723","四川省巴中地区平昌县");
        cityCodes.put("513800","四川省眉山地区");
        cityCodes.put("513821","四川省眉山地区眉山县");
        cityCodes.put("513822","四川省眉山地区仁寿县");
        cityCodes.put("513823","四川省眉山地区彭山县");
        cityCodes.put("513824","四川省眉山地区洪雅县");
        cityCodes.put("513825","四川省眉山地区丹棱县");
        cityCodes.put("513826","四川省眉山地区青神县");
        cityCodes.put("513900","四川省眉山地区资阳地区");
        cityCodes.put("513901","四川省眉山地区资阳市");
        cityCodes.put("513902","四川省眉山地区简阳市");
        cityCodes.put("513921","四川省眉山地区安岳县");
        cityCodes.put("513922","四川省眉山地区乐至县");
        cityCodes.put("520000","贵州省");
        cityCodes.put("520100","贵州省贵阳市");
        cityCodes.put("520101","贵州省贵阳市市辖区");
        cityCodes.put("520102","贵州省贵阳市南明区");
        cityCodes.put("520103","贵州省贵阳市云岩区");
        cityCodes.put("520111","贵州省贵阳市花溪区");
        cityCodes.put("520112","贵州省贵阳市乌当区");
        cityCodes.put("520113","贵州省贵阳市白云区");
        cityCodes.put("520121","贵州省贵阳市开阳县");
        cityCodes.put("520122","贵州省贵阳市息烽县");
        cityCodes.put("520123","贵州省贵阳市修文县");
        cityCodes.put("520181","贵州省贵阳市清镇市");
        cityCodes.put("520200","贵州省六盘水市");
        cityCodes.put("520201","贵州省六盘水市钟山区");
        cityCodes.put("520202","贵州省六盘水市盘县特区");
        cityCodes.put("520203","贵州省六盘水市六枝特区");
        cityCodes.put("520221","贵州省六盘水市水城县");
        cityCodes.put("520300","贵州省遵义市");
        cityCodes.put("520301","贵州省遵义市市辖区");
        cityCodes.put("520302","贵州省遵义市红花岗区");
        cityCodes.put("520321","贵州省遵义市遵义县");
        cityCodes.put("520322","贵州省遵义市桐梓县");
        cityCodes.put("520323","贵州省遵义市绥阳县");
        cityCodes.put("520324","贵州省遵义市正安县");
        cityCodes.put("520325","贵州省遵义市道真仡佬族苗族自治县");
        cityCodes.put("520326","贵州省遵义市务川仡佬族苗族自治县");
        cityCodes.put("520327","贵州省遵义市凤冈县");
        cityCodes.put("520328","贵州省遵义市湄潭县");
        cityCodes.put("520329","贵州省遵义市余庆县");
        cityCodes.put("520330","贵州省遵义市习水县");
        cityCodes.put("520381","贵州省遵义市赤水市");
        cityCodes.put("520382","贵州省遵义市仁怀市");
        cityCodes.put("522200","贵州省铜仁地区");
        cityCodes.put("522201","贵州省铜仁地区铜仁市");
        cityCodes.put("522222","贵州省铜仁地区江口县");
        cityCodes.put("522223","贵州省铜仁地区玉屏侗族自治县");
        cityCodes.put("522224","贵州省铜仁地区石阡县");
        cityCodes.put("522225","贵州省铜仁地区思南县");
        cityCodes.put("522226","贵州省铜仁地区印江土家族苗族自治县");
        cityCodes.put("522227","贵州省铜仁地区德江县");
        cityCodes.put("522228","贵州省铜仁地区沿河土家族自治县");
        cityCodes.put("522229","贵州省铜仁地区松桃苗族自治县");
        cityCodes.put("522230","贵州省铜仁地区万山特区");
        cityCodes.put("522300","贵州省黔西南布依族苗族自治州");
        cityCodes.put("522301","贵州省黔西南布依族苗族自治州兴义市");
        cityCodes.put("522322","贵州省黔西南布依族苗族自治州兴仁县");
        cityCodes.put("522323","贵州省黔西南布依族苗族自治州普安县");
        cityCodes.put("522324","贵州省黔西南布依族苗族自治州晴隆县");
        cityCodes.put("522325","贵州省黔西南布依族苗族自治州贞丰县");
        cityCodes.put("522326","贵州省黔西南布依族苗族自治州望谟县");
        cityCodes.put("522327","贵州省黔西南布依族苗族自治州册亨县");
        cityCodes.put("522328","贵州省黔西南布依族苗族自治州安龙县");
        cityCodes.put("522400","贵州省毕节地区");
        cityCodes.put("522401","贵州省毕节地区毕节市");
        cityCodes.put("522422","贵州省毕节地区大方县");
        cityCodes.put("522423","贵州省毕节地区黔西县");
        cityCodes.put("522424","贵州省毕节地区金沙县");
        cityCodes.put("522425","贵州省毕节地区织金县");
        cityCodes.put("522426","贵州省毕节地区纳雍县");
        cityCodes.put("522427","贵州省毕节地区威宁彝族回族苗族自治县");
        cityCodes.put("522428","贵州省毕节地区赫章县");
        cityCodes.put("522500","贵州省安顺地区");
        cityCodes.put("522501","贵州省安顺地区安顺市");
        cityCodes.put("522526","贵州省安顺地区平坝县");
        cityCodes.put("522527","贵州省安顺地区普定县");
        cityCodes.put("522528","贵州省安顺地区关岭布依族苗族自治县");
        cityCodes.put("522529","贵州省安顺地区镇宁布依族苗族自治县");
        cityCodes.put("522530","贵州省安顺地区紫云苗族布依族自治县");
        cityCodes.put("522600","贵州省黔东南苗族侗族自治州");
        cityCodes.put("522601","贵州省黔东南苗族侗族自治州凯里市");
        cityCodes.put("522622","贵州省黔东南苗族侗族自治州黄平县");
        cityCodes.put("522623","贵州省黔东南苗族侗族自治州施秉县");
        cityCodes.put("522624","贵州省黔东南苗族侗族自治州三穗县");
        cityCodes.put("522625","贵州省黔东南苗族侗族自治州镇远县");
        cityCodes.put("522626","贵州省黔东南苗族侗族自治州岑巩县");
        cityCodes.put("522627","贵州省黔东南苗族侗族自治州天柱县");
        cityCodes.put("522628","贵州省黔东南苗族侗族自治州锦屏县");
        cityCodes.put("522629","贵州省黔东南苗族侗族自治州剑河县");
        cityCodes.put("522630","贵州省黔东南苗族侗族自治州台江县");
        cityCodes.put("522631","贵州省黔东南苗族侗族自治州黎平县");
        cityCodes.put("522632","贵州省黔东南苗族侗族自治州榕江县");
        cityCodes.put("522633","贵州省黔东南苗族侗族自治州从江县");
        cityCodes.put("522634","贵州省黔东南苗族侗族自治州雷山县");
        cityCodes.put("522635","贵州省黔东南苗族侗族自治州麻江县");
        cityCodes.put("522636","贵州省黔东南苗族侗族自治州丹寨县");
        cityCodes.put("522700","贵州省黔南布依族苗族自治州");
        cityCodes.put("522701","贵州省黔南布依族苗族自治州都匀市");
        cityCodes.put("522702","贵州省黔南布依族苗族自治州福泉市");
        cityCodes.put("522722","贵州省黔南布依族苗族自治州荔波县");
        cityCodes.put("522723","贵州省黔南布依族苗族自治州贵定县");
        cityCodes.put("522725","贵州省黔南布依族苗族自治州瓮安县");
        cityCodes.put("522726","贵州省黔南布依族苗族自治州独山县");
        cityCodes.put("522727","贵州省黔南布依族苗族自治州平塘县");
        cityCodes.put("522728","贵州省黔南布依族苗族自治州罗甸县");
        cityCodes.put("522729","贵州省黔南布依族苗族自治州长顺县");
        cityCodes.put("522730","贵州省黔南布依族苗族自治州龙里县");
        cityCodes.put("522731","贵州省黔南布依族苗族自治州惠水县");
        cityCodes.put("522732","贵州省黔南布依族苗族自治州三都水族自治县");
        cityCodes.put("530000","云南省");
        cityCodes.put("530100","云南省昆明市");
        cityCodes.put("530101","云南省昆明市市辖区");
        cityCodes.put("530102","云南省昆明市五华区");
        cityCodes.put("530103","云南省昆明市盘龙区");
        cityCodes.put("530111","云南省昆明市官渡区");
        cityCodes.put("530112","云南省昆明市西山区");
        cityCodes.put("530113","云南省昆明市东川区");
        cityCodes.put("530121","云南省昆明市呈贡县");
        cityCodes.put("530122","云南省昆明市晋宁县");
        cityCodes.put("530124","云南省昆明市富民县");
        cityCodes.put("530125","云南省昆明市宜良县");
        cityCodes.put("530126","云南省昆明市石林彝族自治县");
        cityCodes.put("530127","云南省昆明市嵩明县");
        cityCodes.put("530128","云南省昆明市禄劝彝族苗族自治县");
        cityCodes.put("530129","云南省昆明市寻甸回族彝族自治县");
        cityCodes.put("530181","云南省昆明市安宁市");
        cityCodes.put("530300","云南省曲靖市");
        cityCodes.put("530301","云南省曲靖市市辖区");
        cityCodes.put("530302","云南省曲靖市麒麟区");
        cityCodes.put("530321","云南省曲靖市马龙县");
        cityCodes.put("530322","云南省曲靖市陆良县");
        cityCodes.put("530323","云南省曲靖市师宗县");
        cityCodes.put("530324","云南省曲靖市罗平县");
        cityCodes.put("530325","云南省曲靖市富源县");
        cityCodes.put("530326","云南省曲靖市会泽县");
        cityCodes.put("530328","云南省曲靖市沾益县");
        cityCodes.put("530381","云南省曲靖市宣威市");
        cityCodes.put("530400","云南省玉溪市");
        cityCodes.put("530401","云南省玉溪市市辖区");
        cityCodes.put("530402","云南省玉溪市红塔区");
        cityCodes.put("530421","云南省玉溪市江川县");
        cityCodes.put("530422","云南省玉溪市澄江县");
        cityCodes.put("530423","云南省玉溪市通海县");
        cityCodes.put("530424","云南省玉溪市华宁县");
        cityCodes.put("530425","云南省玉溪市易门县");
        cityCodes.put("530426","云南省玉溪市峨山彝族自治县");
        cityCodes.put("530427","云南省玉溪市新平彝族傣族自治县");
        cityCodes.put("530428","云南省玉溪市元江哈尼族彝族傣族自治县");
        cityCodes.put("532100","云南省昭通地区");
        cityCodes.put("532101","云南省昭通地区昭通市");
        cityCodes.put("532122","云南省昭通地区鲁甸县");
        cityCodes.put("532123","云南省昭通地区巧家县");
        cityCodes.put("532124","云南省昭通地区盐津县");
        cityCodes.put("532125","云南省昭通地区大关县");
        cityCodes.put("532126","云南省昭通地区永善县");
        cityCodes.put("532127","云南省昭通地区绥江县");
        cityCodes.put("532128","云南省昭通地区镇雄县");
        cityCodes.put("532129","云南省昭通地区彝良县");
        cityCodes.put("532130","云南省昭通地区威信县");
        cityCodes.put("532131","云南省昭通地区水富县");
        cityCodes.put("532300","云南省楚雄彝族自治州");
        cityCodes.put("532301","云南省楚雄彝族自治州楚雄市");
        cityCodes.put("532322","云南省楚雄彝族自治州双柏县");
        cityCodes.put("532323","云南省楚雄彝族自治州牟定县");
        cityCodes.put("532324","云南省楚雄彝族自治州南华县");
        cityCodes.put("532325","云南省楚雄彝族自治州姚安县");
        cityCodes.put("532326","云南省楚雄彝族自治州大姚县");
        cityCodes.put("532327","云南省楚雄彝族自治州永仁县");
        cityCodes.put("532328","云南省楚雄彝族自治州元谋县");
        cityCodes.put("532329","云南省楚雄彝族自治州武定县");
        cityCodes.put("532331","云南省楚雄彝族自治州禄丰县");
        cityCodes.put("532500","云南省红河哈尼族彝族自治州");
        cityCodes.put("532501","云南省红河哈尼族彝族自治州个旧市");
        cityCodes.put("532502","云南省红河哈尼族彝族自治州开远市");
        cityCodes.put("532522","云南省红河哈尼族彝族自治州蒙自县");
        cityCodes.put("532523","云南省红河哈尼族彝族自治州屏边苗族自治县");
        cityCodes.put("532524","云南省红河哈尼族彝族自治州建水县");
        cityCodes.put("532525","云南省红河哈尼族彝族自治州石屏县");
        cityCodes.put("532526","云南省红河哈尼族彝族自治州弥勒县");
        cityCodes.put("532527","云南省红河哈尼族彝族自治州泸西县");
        cityCodes.put("532528","云南省红河哈尼族彝族自治州元阳县");
        cityCodes.put("532529","云南省红河哈尼族彝族自治州红河县");
        cityCodes.put("532530","云南省红河哈尼族彝族自治州金平苗族瑶族傣族自治县");
        cityCodes.put("532531","云南省红河哈尼族彝族自治州绿春县");
        cityCodes.put("532532","云南省红河哈尼族彝族自治州河口瑶族自治县");
        cityCodes.put("532600","云南省文山壮族苗族自治州");
        cityCodes.put("532621","云南省文山壮族苗族自治州文山县");
        cityCodes.put("532622","云南省文山壮族苗族自治州砚山县");
        cityCodes.put("532623","云南省文山壮族苗族自治州西畴县");
        cityCodes.put("532624","云南省文山壮族苗族自治州麻栗坡县");
        cityCodes.put("532625","云南省文山壮族苗族自治州马关县");
        cityCodes.put("532626","云南省文山壮族苗族自治州丘北县");
        cityCodes.put("532627","云南省文山壮族苗族自治州广南县");
        cityCodes.put("532628","云南省文山壮族苗族自治州富宁县");
        cityCodes.put("532700","云南省思茅地区");
        cityCodes.put("532701","云南省思茅地区思茅市");
        cityCodes.put("532722","云南省思茅地区普洱哈尼族彝族自治县");
        cityCodes.put("532723","云南省思茅地区墨江哈尼族自治县");
        cityCodes.put("532724","云南省思茅地区景东彝族自治县");
        cityCodes.put("532725","云南省思茅地区景谷傣族彝族自治县");
        cityCodes.put("532726","云南省思茅地区镇沅彝族哈尼族拉祜族自治县");
        cityCodes.put("532727","云南省思茅地区江城哈尼族彝族自治县");
        cityCodes.put("532728","云南省思茅地区孟连傣族拉祜族佤族自治县");
        cityCodes.put("532729","云南省思茅地区澜沧拉祜族自治县");
        cityCodes.put("532730","云南省思茅地区西盟佤族自治县");
        cityCodes.put("532800","云南省西双版纳傣族自治州");
        cityCodes.put("532801","云南省西双版纳傣族自治州景洪市");
        cityCodes.put("532822","云南省西双版纳傣族自治州勐海县");
        cityCodes.put("532823","云南省西双版纳傣族自治州勐腊县");
        cityCodes.put("532900","云南省大理白族自治州");
        cityCodes.put("532901","云南省大理白族自治州大理市");
        cityCodes.put("532922","云南省大理白族自治州漾濞彝族自治县");
        cityCodes.put("532923","云南省大理白族自治州祥云县");
        cityCodes.put("532924","云南省大理白族自治州宾川县");
        cityCodes.put("532925","云南省大理白族自治州弥渡县");
        cityCodes.put("532926","云南省大理白族自治州南涧彝族自治县");
        cityCodes.put("532927","云南省大理白族自治州巍山彝族回族自治县");
        cityCodes.put("532928","云南省大理白族自治州永平县");
        cityCodes.put("532929","云南省大理白族自治州云龙县");
        cityCodes.put("532930","云南省大理白族自治州洱源县");
        cityCodes.put("532931","云南省大理白族自治州剑川县");
        cityCodes.put("532932","云南省大理白族自治州鹤庆县");
        cityCodes.put("533000","云南省保山地区");
        cityCodes.put("533001","云南省保山地区保山市");
        cityCodes.put("533022","云南省保山地区施甸县");
        cityCodes.put("533023","云南省保山地区腾冲县");
        cityCodes.put("533024","云南省保山地区龙陵县");
        cityCodes.put("533025","云南省保山地区昌宁县");
        cityCodes.put("533100","云南省德宏傣族景颇族自治州");
        cityCodes.put("533101","云南省德宏傣族景颇族自治州畹町市");
        cityCodes.put("533102","云南省德宏傣族景颇族自治州瑞丽市");
        cityCodes.put("533103","云南省德宏傣族景颇族自治州潞西市");
        cityCodes.put("533122","云南省德宏傣族景颇族自治州梁河县");
        cityCodes.put("533123","云南省德宏傣族景颇族自治州盈江县");
        cityCodes.put("533124","云南省德宏傣族景颇族自治州陇川县");
        cityCodes.put("533200","云南省丽江地区");
        cityCodes.put("533221","云南省丽江地区丽江纳西族自治县");
        cityCodes.put("533222","云南省丽江地区永胜县");
        cityCodes.put("533223","云南省丽江地区华坪县");
        cityCodes.put("533224","云南省丽江地区宁蒗彝族自治县");
        cityCodes.put("533300","云南省怒江傈僳族自治州");
        cityCodes.put("533321","云南省怒江傈僳族自治州泸水县");
        cityCodes.put("533323","云南省怒江傈僳族自治州福贡县");
        cityCodes.put("533324","云南省怒江傈僳族自治州贡山独龙族怒族自治县");
        cityCodes.put("533325","云南省怒江傈僳族自治州兰坪白族普米族自治县");
        cityCodes.put("533400","云南省迪庆藏族自治州");
        cityCodes.put("533421","云南省迪庆藏族自治州中甸县");
        cityCodes.put("533422","云南省迪庆藏族自治州德钦县");
        cityCodes.put("533423","云南省迪庆藏族自治州维西傈僳族自治县");
        cityCodes.put("533500","云南省临沧地区");
        cityCodes.put("533521","云南省临沧地区临沧县");
        cityCodes.put("533522","云南省临沧地区凤庆县");
        cityCodes.put("533523","云南省临沧地区云县");
        cityCodes.put("533524","云南省临沧地区永德县");
        cityCodes.put("533525","云南省临沧地区镇康县");
        cityCodes.put("533526","云南省临沧地区双江拉祜族佤族布朗族傣族自治县");
        cityCodes.put("533527","云南省临沧地区耿马傣族佤族自治县");
        cityCodes.put("533528","云南省临沧地区沧源佤族自治县");
        cityCodes.put("540000","西藏自治区");
        cityCodes.put("540100","西藏自治区拉萨市");
        cityCodes.put("540101","西藏自治区拉萨市市辖区");
        cityCodes.put("540102","西藏自治区拉萨市城关区");
        cityCodes.put("540121","西藏自治区拉萨市林周县");
        cityCodes.put("540122","西藏自治区拉萨市当雄县");
        cityCodes.put("540123","西藏自治区拉萨市尼木县");
        cityCodes.put("540124","西藏自治区拉萨市曲水县");
        cityCodes.put("540125","西藏自治区拉萨市堆龙德庆县");
        cityCodes.put("540126","西藏自治区拉萨市达孜县");
        cityCodes.put("540127","西藏自治区拉萨市墨竹工卡县");
        cityCodes.put("542100","西藏自治区昌都地区");
        cityCodes.put("542121","西藏自治区昌都地区昌都县");
        cityCodes.put("542122","西藏自治区昌都地区江达县");
        cityCodes.put("542123","西藏自治区昌都地区贡觉县");
        cityCodes.put("542124","西藏自治区昌都地区类乌齐县");
        cityCodes.put("542125","西藏自治区昌都地区丁青县");
        cityCodes.put("542126","西藏自治区昌都地区察雅县");
        cityCodes.put("542127","西藏自治区昌都地区八宿县");
        cityCodes.put("542128","西藏自治区昌都地区左贡县");
        cityCodes.put("542129","西藏自治区昌都地区芒康县");
        cityCodes.put("542132","西藏自治区昌都地区洛隆县");
        cityCodes.put("542133","西藏自治区昌都地区边坝县");
        cityCodes.put("542134","西藏自治区昌都地区盐井县");
        cityCodes.put("542135","西藏自治区昌都地区碧土县");
        cityCodes.put("542136","西藏自治区昌都地区妥坝县");
        cityCodes.put("542137","西藏自治区昌都地区生达县");
        cityCodes.put("542200","西藏自治区山南地区");
        cityCodes.put("542221","西藏自治区山南地区乃东县");
        cityCodes.put("542222","西藏自治区山南地区扎囊县");
        cityCodes.put("542223","西藏自治区山南地区贡嘎县");
        cityCodes.put("542224","西藏自治区山南地区桑日县");
        cityCodes.put("542225","西藏自治区山南地区琼结县");
        cityCodes.put("542226","西藏自治区山南地区曲松县");
        cityCodes.put("542227","西藏自治区山南地区措美县");
        cityCodes.put("542228","西藏自治区山南地区洛扎县");
        cityCodes.put("542229","西藏自治区山南地区加查县");
        cityCodes.put("542231","西藏自治区山南地区隆子县");
        cityCodes.put("542232","西藏自治区山南地区错那县");
        cityCodes.put("542233","西藏自治区山南地区浪卡子县");
        cityCodes.put("542300","西藏自治区日喀则地区");
        cityCodes.put("542301","西藏自治区日喀则地区日喀则市");
        cityCodes.put("542322","西藏自治区日喀则地区南木林县");
        cityCodes.put("542323","西藏自治区日喀则地区江孜县");
        cityCodes.put("542324","西藏自治区日喀则地区定日县");
        cityCodes.put("542325","西藏自治区日喀则地区萨迦县");
        cityCodes.put("542326","西藏自治区日喀则地区拉孜县");
        cityCodes.put("542327","西藏自治区日喀则地区昂仁县");
        cityCodes.put("542328","西藏自治区日喀则地区谢通门县");
        cityCodes.put("542329","西藏自治区日喀则地区白朗县");
        cityCodes.put("542330","西藏自治区日喀则地区仁布县");
        cityCodes.put("542331","西藏自治区日喀则地区康马县");
        cityCodes.put("542332","西藏自治区日喀则地区定结县");
        cityCodes.put("542333","西藏自治区日喀则地区仲巴县");
        cityCodes.put("542334","西藏自治区日喀则地区亚东县");
        cityCodes.put("542335","西藏自治区日喀则地区吉隆县");
        cityCodes.put("542336","西藏自治区日喀则地区聂拉木县");
        cityCodes.put("542337","西藏自治区日喀则地区萨嘎县");
        cityCodes.put("542338","西藏自治区日喀则地区岗巴县");
        cityCodes.put("542400","西藏自治区那曲地区");
        cityCodes.put("542421","西藏自治区那曲地区那曲县");
        cityCodes.put("542422","西藏自治区那曲地区嘉黎县");
        cityCodes.put("542423","西藏自治区那曲地区比如县");
        cityCodes.put("542424","西藏自治区那曲地区聂荣县");
        cityCodes.put("542425","西藏自治区那曲地区安多县");
        cityCodes.put("542426","西藏自治区那曲地区申扎县");
        cityCodes.put("542427","西藏自治区那曲地区索县");
        cityCodes.put("542428","西藏自治区那曲地区班戈县");
        cityCodes.put("542429","西藏自治区那曲地区巴青县");
        cityCodes.put("542430","西藏自治区那曲地区尼玛县");
        cityCodes.put("542500","西藏自治区阿里地区");
        cityCodes.put("542521","西藏自治区阿里地区普兰县");
        cityCodes.put("542522","西藏自治区阿里地区札达县");
        cityCodes.put("542523","西藏自治区阿里地区噶尔县");
        cityCodes.put("542524","西藏自治区阿里地区日土县");
        cityCodes.put("542525","西藏自治区阿里地区革吉县");
        cityCodes.put("542526","西藏自治区阿里地区改则县");
        cityCodes.put("542527","西藏自治区阿里地区措勤县");
        cityCodes.put("542528","西藏自治区阿里地区隆格尔县");
        cityCodes.put("542600","西藏自治区林芝地区");
        cityCodes.put("542621","西藏自治区林芝地区林芝县");
        cityCodes.put("542622","西藏自治区林芝地区工布江达县");
        cityCodes.put("542623","西藏自治区林芝地区米林县");
        cityCodes.put("542624","西藏自治区林芝地区墨脱县");
        cityCodes.put("542625","西藏自治区林芝地区波密县");
        cityCodes.put("542626","西藏自治区林芝地区察隅县");
        cityCodes.put("542627","西藏自治区林芝地区朗县");
        cityCodes.put("610000","陕西省");
        cityCodes.put("610100","陕西省西安市");
        cityCodes.put("610101","陕西省西安市市辖区");
        cityCodes.put("610102","陕西省西安市新城区");
        cityCodes.put("610103","陕西省西安市碑林区");
        cityCodes.put("610104","陕西省西安市莲湖区");
        cityCodes.put("610111","陕西省西安市灞桥区");
        cityCodes.put("610112","陕西省西安市未央区");
        cityCodes.put("610113","陕西省西安市雁塔区");
        cityCodes.put("610114","陕西省西安市阎良区");
        cityCodes.put("610115","陕西省西安市临潼区");
        cityCodes.put("610121","陕西省西安市长安县");
        cityCodes.put("610122","陕西省西安市蓝田县");
        cityCodes.put("610124","陕西省西安市周至县");
        cityCodes.put("610125","陕西省西安市户县");
        cityCodes.put("610126","陕西省西安市高陵县");
        cityCodes.put("610200","陕西省铜川市");
        cityCodes.put("610201","陕西省铜川市市辖区");
        cityCodes.put("610202","陕西省铜川市城区");
        cityCodes.put("610203","陕西省铜川市郊区");
        cityCodes.put("610221","陕西省铜川市耀县");
        cityCodes.put("610222","陕西省铜川市宜君县");
        cityCodes.put("610300","陕西省宝鸡市");
        cityCodes.put("610301","陕西省宝鸡市市辖区");
        cityCodes.put("610302","陕西省宝鸡市渭滨区");
        cityCodes.put("610303","陕西省宝鸡市金台区");
        cityCodes.put("610321","陕西省宝鸡市宝鸡县");
        cityCodes.put("610322","陕西省宝鸡市凤翔县");
        cityCodes.put("610323","陕西省宝鸡市岐山县");
        cityCodes.put("610324","陕西省宝鸡市扶风县");
        cityCodes.put("610326","陕西省宝鸡市眉县");
        cityCodes.put("610327","陕西省宝鸡市陇县");
        cityCodes.put("610328","陕西省宝鸡市千阳县");
        cityCodes.put("610329","陕西省宝鸡市麟游县");
        cityCodes.put("610330","陕西省宝鸡市凤县");
        cityCodes.put("610331","陕西省宝鸡市太白县");
        cityCodes.put("610400","陕西省咸阳市");
        cityCodes.put("610401","陕西省咸阳市市辖区");
        cityCodes.put("610402","陕西省咸阳市秦都区");
        cityCodes.put("610403","陕西省咸阳市杨陵区");
        cityCodes.put("610404","陕西省咸阳市渭城区");
        cityCodes.put("610422","陕西省咸阳市三原县");
        cityCodes.put("610423","陕西省咸阳市泾阳县");
        cityCodes.put("610424","陕西省咸阳市乾县");
        cityCodes.put("610425","陕西省咸阳市礼泉县");
        cityCodes.put("610426","陕西省咸阳市永寿县");
        cityCodes.put("610427","陕西省咸阳市彬县");
        cityCodes.put("610428","陕西省咸阳市长武县");
        cityCodes.put("610429","陕西省咸阳市旬邑县");
        cityCodes.put("610430","陕西省咸阳市淳化县");
        cityCodes.put("610431","陕西省咸阳市武功县");
        cityCodes.put("610481","陕西省咸阳市兴平市");
        cityCodes.put("610500","陕西省渭南市");
        cityCodes.put("610501","陕西省渭南市市辖区");
        cityCodes.put("610502","陕西省渭南市临渭区");
        cityCodes.put("610521","陕西省渭南市华县");
        cityCodes.put("610522","陕西省渭南市潼关县");
        cityCodes.put("610523","陕西省渭南市大荔县");
        cityCodes.put("610524","陕西省渭南市合阳县");
        cityCodes.put("610525","陕西省渭南市澄城县");
        cityCodes.put("610526","陕西省渭南市蒲城县");
        cityCodes.put("610527","陕西省渭南市白水县");
        cityCodes.put("610528","陕西省渭南市富平县");
        cityCodes.put("610581","陕西省渭南市韩城市");
        cityCodes.put("610582","陕西省渭南市华阴市");
        cityCodes.put("610600","陕西省延安市");
        cityCodes.put("610601","陕西省延安市市辖区");
        cityCodes.put("610602","陕西省延安市宝塔区");
        cityCodes.put("610621","陕西省延安市延长县");
        cityCodes.put("610622","陕西省延安市延川县");
        cityCodes.put("610623","陕西省延安市子长县");
        cityCodes.put("610624","陕西省延安市安塞县");
        cityCodes.put("610625","陕西省延安市志丹县");
        cityCodes.put("610626","陕西省延安市吴旗县");
        cityCodes.put("610627","陕西省延安市甘泉县");
        cityCodes.put("610628","陕西省延安市富县");
        cityCodes.put("610629","陕西省延安市洛川县");
        cityCodes.put("610630","陕西省延安市宜川县");
        cityCodes.put("610631","陕西省延安市黄龙县");
        cityCodes.put("610632","陕西省延安市黄陵县");
        cityCodes.put("610700","陕西省汉中市");
        cityCodes.put("610701","陕西省汉中市市辖区");
        cityCodes.put("610702","陕西省汉中市汉台区");
        cityCodes.put("610721","陕西省汉中市南郑县");
        cityCodes.put("610722","陕西省汉中市城固县");
        cityCodes.put("610723","陕西省汉中市洋县");
        cityCodes.put("610724","陕西省汉中市西乡县");
        cityCodes.put("610725","陕西省汉中市勉县");
        cityCodes.put("610726","陕西省汉中市宁强县");
        cityCodes.put("610727","陕西省汉中市略阳县");
        cityCodes.put("610728","陕西省汉中市镇巴县");
        cityCodes.put("610729","陕西省汉中市留坝县");
        cityCodes.put("610730","陕西省汉中市佛坪县");
        cityCodes.put("612400","陕西省安康地区");
        cityCodes.put("612401","陕西省安康地区安康市");
        cityCodes.put("612422","陕西省安康地区汉阴县");
        cityCodes.put("612423","陕西省安康地区石泉县");
        cityCodes.put("612424","陕西省安康地区宁陕县");
        cityCodes.put("612425","陕西省安康地区紫阳县");
        cityCodes.put("612426","陕西省安康地区岚皋县");
        cityCodes.put("612427","陕西省安康地区平利县");
        cityCodes.put("612428","陕西省安康地区镇坪县");
        cityCodes.put("612429","陕西省安康地区旬阳县");
        cityCodes.put("612430","陕西省安康地区白河县");
        cityCodes.put("612500","陕西省商洛地区");
        cityCodes.put("612501","陕西省商洛地区商州市");
        cityCodes.put("612522","陕西省商洛地区洛南县");
        cityCodes.put("612523","陕西省商洛地区丹凤县");
        cityCodes.put("612524","陕西省商洛地区商南县");
        cityCodes.put("612525","陕西省商洛地区山阳县");
        cityCodes.put("612526","陕西省商洛地区镇安县");
        cityCodes.put("612527","陕西省商洛地区柞水县");
        cityCodes.put("612700","陕西省榆林地区");
        cityCodes.put("612701","陕西省榆林地区榆林市");
        cityCodes.put("612722","陕西省榆林地区神木县");
        cityCodes.put("612723","陕西省榆林地区府谷县");
        cityCodes.put("612724","陕西省榆林地区横山县");
        cityCodes.put("612725","陕西省榆林地区靖边县");
        cityCodes.put("612726","陕西省榆林地区定边县");
        cityCodes.put("612727","陕西省榆林地区绥德县");
        cityCodes.put("612728","陕西省榆林地区米脂县");
        cityCodes.put("612729","陕西省榆林地区佳县");
        cityCodes.put("612730","陕西省榆林地区吴堡县");
        cityCodes.put("612731","陕西省榆林地区清涧县");
        cityCodes.put("612732","陕西省榆林地区子洲县");
        cityCodes.put("620000","甘肃省");
        cityCodes.put("620100","甘肃省兰州市");
        cityCodes.put("620101","甘肃省兰州市市辖区");
        cityCodes.put("620102","甘肃省兰州市城关区");
        cityCodes.put("620103","甘肃省兰州市七里河区");
        cityCodes.put("620104","甘肃省兰州市西固区");
        cityCodes.put("620105","甘肃省兰州市安宁区");
        cityCodes.put("620111","甘肃省兰州市红古区");
        cityCodes.put("620121","甘肃省兰州市永登县");
        cityCodes.put("620122","甘肃省兰州市皋兰县");
        cityCodes.put("620123","甘肃省兰州市榆中县");
        cityCodes.put("620200","甘肃省嘉峪关市");
        cityCodes.put("620201","甘肃省嘉峪关市市辖区");
        cityCodes.put("620300","甘肃省嘉峪关市金昌市");
        cityCodes.put("620301","甘肃省嘉峪关市市辖区");
        cityCodes.put("620302","甘肃省嘉峪关市金川区");
        cityCodes.put("620321","甘肃省嘉峪关市永昌县");
        cityCodes.put("620400","甘肃省白银市");
        cityCodes.put("620401","甘肃省白银市市辖区");
        cityCodes.put("620402","甘肃省白银市白银区");
        cityCodes.put("620403","甘肃省白银市平川区");
        cityCodes.put("620421","甘肃省白银市靖远县");
        cityCodes.put("620422","甘肃省白银市会宁县");
        cityCodes.put("620423","甘肃省白银市景泰县");
        cityCodes.put("620500","甘肃省天水市");
        cityCodes.put("620501","甘肃省天水市市辖区");
        cityCodes.put("620502","甘肃省天水市秦城区");
        cityCodes.put("620503","甘肃省天水市北道区");
        cityCodes.put("620521","甘肃省天水市清水县");
        cityCodes.put("620522","甘肃省天水市秦安县");
        cityCodes.put("620523","甘肃省天水市甘谷县");
        cityCodes.put("620524","甘肃省天水市武山县");
        cityCodes.put("620525","甘肃省天水市张家川回族自治县");
        cityCodes.put("622100","甘肃省酒泉地区");
        cityCodes.put("622101","甘肃省酒泉地区玉门市");
        cityCodes.put("622102","甘肃省酒泉地区酒泉市");
        cityCodes.put("622103","甘肃省酒泉地区敦煌市");
        cityCodes.put("622123","甘肃省酒泉地区金塔县");
        cityCodes.put("622124","甘肃省酒泉地区肃北蒙古族自治县");
        cityCodes.put("622125","甘肃省酒泉地区阿克塞哈萨克族自治县");
        cityCodes.put("622126","甘肃省酒泉地区安西县");
        cityCodes.put("622200","甘肃省张掖地区");
        cityCodes.put("622201","甘肃省张掖地区张掖市");
        cityCodes.put("622222","甘肃省张掖地区肃南裕固族自治县");
        cityCodes.put("622223","甘肃省张掖地区民乐县");
        cityCodes.put("622224","甘肃省张掖地区临泽县");
        cityCodes.put("622225","甘肃省张掖地区高台县");
        cityCodes.put("622226","甘肃省张掖地区山丹县");
        cityCodes.put("622300","甘肃省武威地区");
        cityCodes.put("622301","甘肃省武威地区武威市");
        cityCodes.put("622322","甘肃省武威地区民勤县");
        cityCodes.put("622323","甘肃省武威地区古浪县");
        cityCodes.put("622326","甘肃省武威地区天祝藏族自治县");
        cityCodes.put("622400","甘肃省定西地区");
        cityCodes.put("622421","甘肃省定西地区定西县");
        cityCodes.put("622424","甘肃省定西地区通渭县");
        cityCodes.put("622425","甘肃省定西地区陇西县");
        cityCodes.put("622426","甘肃省定西地区渭源县");
        cityCodes.put("622427","甘肃省定西地区临洮县");
        cityCodes.put("622428","甘肃省定西地区漳县");
        cityCodes.put("622429","甘肃省定西地区岷县");
        cityCodes.put("622600","甘肃省陇南地区");
        cityCodes.put("622621","甘肃省陇南地区武都县");
        cityCodes.put("622623","甘肃省陇南地区宕昌县");
        cityCodes.put("622624","甘肃省陇南地区成县");
        cityCodes.put("622625","甘肃省陇南地区康县");
        cityCodes.put("622626","甘肃省陇南地区文县");
        cityCodes.put("622627","甘肃省陇南地区西和县");
        cityCodes.put("622628","甘肃省陇南地区礼县");
        cityCodes.put("622629","甘肃省陇南地区两当县");
        cityCodes.put("622630","甘肃省陇南地区徽县");
        cityCodes.put("622700","甘肃省平凉地区");
        cityCodes.put("622701","甘肃省平凉地区平凉市");
        cityCodes.put("622722","甘肃省平凉地区泾川县");
        cityCodes.put("622723","甘肃省平凉地区灵台县");
        cityCodes.put("622724","甘肃省平凉地区崇信县");
        cityCodes.put("622725","甘肃省平凉地区华亭县");
        cityCodes.put("622726","甘肃省平凉地区庄浪县");
        cityCodes.put("622727","甘肃省平凉地区静宁县");
        cityCodes.put("622800","甘肃省庆阳地区");
        cityCodes.put("622801","甘肃省庆阳地区西峰市");
        cityCodes.put("622821","甘肃省庆阳地区庆阳县");
        cityCodes.put("622822","甘肃省庆阳地区环县");
        cityCodes.put("622823","甘肃省庆阳地区华池县");
        cityCodes.put("622824","甘肃省庆阳地区合水县");
        cityCodes.put("622825","甘肃省庆阳地区正宁县");
        cityCodes.put("622826","甘肃省庆阳地区宁县");
        cityCodes.put("622827","甘肃省庆阳地区镇原县");
        cityCodes.put("622900","甘肃省临夏回族自治州");
        cityCodes.put("622901","甘肃省临夏回族自治州临夏市");
        cityCodes.put("622921","甘肃省临夏回族自治州临夏县");
        cityCodes.put("622922","甘肃省临夏回族自治州康乐县");
        cityCodes.put("622923","甘肃省临夏回族自治州永靖县");
        cityCodes.put("622924","甘肃省临夏回族自治州广河县");
        cityCodes.put("622925","甘肃省临夏回族自治州和政县");
        cityCodes.put("622926","甘肃省临夏回族自治州东乡族自治县");
        cityCodes.put("622927","甘肃省临夏回族自治州积石山保安族东乡族撒拉族自治县");
        cityCodes.put("623000","甘肃省甘南藏族自治州");
        cityCodes.put("623001","甘肃省甘南藏族自治州合作市");
        cityCodes.put("623021","甘肃省甘南藏族自治州临潭县");
        cityCodes.put("623022","甘肃省甘南藏族自治州卓尼县");
        cityCodes.put("623023","甘肃省甘南藏族自治州舟曲县");
        cityCodes.put("623024","甘肃省甘南藏族自治州迭部县");
        cityCodes.put("623025","甘肃省甘南藏族自治州玛曲县");
        cityCodes.put("623026","甘肃省甘南藏族自治州碌曲县");
        cityCodes.put("623027","甘肃省甘南藏族自治州夏河县");
        cityCodes.put("630000","青海省");
        cityCodes.put("630100","青海省西宁市");
        cityCodes.put("630101","青海省西宁市市辖区");
        cityCodes.put("630102","青海省西宁市城东区");
        cityCodes.put("630103","青海省西宁市城中区");
        cityCodes.put("630104","青海省西宁市城西区");
        cityCodes.put("630105","青海省西宁市城北区");
        cityCodes.put("630121","青海省西宁市大通回族土族自治县");
        cityCodes.put("632100","青海省海东地区");
        cityCodes.put("632121","青海省海东地区平安县");
        cityCodes.put("632122","青海省海东地区民和回族土族自治县");
        cityCodes.put("632123","青海省海东地区乐都县");
        cityCodes.put("632124","青海省海东地区湟中县");
        cityCodes.put("632125","青海省海东地区湟源县");
        cityCodes.put("632126","青海省海东地区互助土族自治县");
        cityCodes.put("632127","青海省海东地区化隆回族自治县");
        cityCodes.put("632128","青海省海东地区循化撒拉族自治县");
        cityCodes.put("632200","青海省海北藏族自治州");
        cityCodes.put("632221","青海省海北藏族自治州门源回族自治县");
        cityCodes.put("632222","青海省海北藏族自治州祁连县");
        cityCodes.put("632223","青海省海北藏族自治州海晏县");
        cityCodes.put("632224","青海省海北藏族自治州刚察县");
        cityCodes.put("632300","青海省黄南藏族自治州");
        cityCodes.put("632321","青海省黄南藏族自治州同仁县");
        cityCodes.put("632322","青海省黄南藏族自治州尖扎县");
        cityCodes.put("632323","青海省黄南藏族自治州泽库县");
        cityCodes.put("632324","青海省黄南藏族自治州河南蒙古族自治县");
        cityCodes.put("632500","青海省海南藏族自治州");
        cityCodes.put("632521","青海省海南藏族自治州共和县");
        cityCodes.put("632522","青海省海南藏族自治州同德县");
        cityCodes.put("632523","青海省海南藏族自治州贵德县");
        cityCodes.put("632524","青海省海南藏族自治州兴海县");
        cityCodes.put("632525","青海省海南藏族自治州贵南县");
        cityCodes.put("632600","青海省果洛藏族自治州");
        cityCodes.put("632621","青海省果洛藏族自治州玛沁县");
        cityCodes.put("632622","青海省果洛藏族自治州班玛县");
        cityCodes.put("632623","青海省果洛藏族自治州甘德县");
        cityCodes.put("632624","青海省果洛藏族自治州达日县");
        cityCodes.put("632625","青海省果洛藏族自治州久治县");
        cityCodes.put("632626","青海省果洛藏族自治州玛多县");
        cityCodes.put("632700","青海省玉树藏族自治州");
        cityCodes.put("632721","青海省玉树藏族自治州玉树县");
        cityCodes.put("632722","青海省玉树藏族自治州杂多县");
        cityCodes.put("632723","青海省玉树藏族自治州称多县");
        cityCodes.put("632724","青海省玉树藏族自治州治多县");
        cityCodes.put("632725","青海省玉树藏族自治州囊谦县");
        cityCodes.put("632726","青海省玉树藏族自治州曲麻莱县");
        cityCodes.put("632800","青海省海西蒙古族藏族自治州");
        cityCodes.put("632801","青海省海西蒙古族藏族自治州格尔木市");
        cityCodes.put("632802","青海省海西蒙古族藏族自治州德令哈市");
        cityCodes.put("632821","青海省海西蒙古族藏族自治州乌兰县");
        cityCodes.put("632822","青海省海西蒙古族藏族自治州都兰县");
        cityCodes.put("632823","青海省海西蒙古族藏族自治州天峻县");
        cityCodes.put("640000","宁夏回族自治区");
        cityCodes.put("640100","宁夏回族自治区银川市");
        cityCodes.put("640101","宁夏回族自治区银川市市辖区");
        cityCodes.put("640102","宁夏回族自治区银川市城区");
        cityCodes.put("640103","宁夏回族自治区银川市新城区");
        cityCodes.put("640111","宁夏回族自治区银川市郊区");
        cityCodes.put("640121","宁夏回族自治区银川市永宁县");
        cityCodes.put("640122","宁夏回族自治区银川市贺兰县");
        cityCodes.put("640200","宁夏回族自治区石嘴山市");
        cityCodes.put("640201","宁夏回族自治区石嘴山市市辖区");
        cityCodes.put("640202","宁夏回族自治区石嘴山市大武口区");
        cityCodes.put("640203","宁夏回族自治区石嘴山市石嘴山区");
        cityCodes.put("640204","宁夏回族自治区石嘴山市石炭井区");
        cityCodes.put("640221","宁夏回族自治区石嘴山市平罗县");
        cityCodes.put("640222","宁夏回族自治区石嘴山市陶乐县");
        cityCodes.put("640223","宁夏回族自治区石嘴山市惠农县");
        cityCodes.put("640300","宁夏回族自治区吴忠市");
        cityCodes.put("640301","宁夏回族自治区吴忠市市辖区");
        cityCodes.put("640302","宁夏回族自治区吴忠市利通区");
        cityCodes.put("640321","宁夏回族自治区吴忠市中卫县");
        cityCodes.put("640322","宁夏回族自治区吴忠市中宁县");
        cityCodes.put("640323","宁夏回族自治区吴忠市盐池县");
        cityCodes.put("640324","宁夏回族自治区吴忠市同心县");
        cityCodes.put("640381","宁夏回族自治区吴忠市青铜峡市");
        cityCodes.put("640382","宁夏回族自治区吴忠市灵武市");
        cityCodes.put("642200","宁夏回族自治区固原地区");
        cityCodes.put("642221","宁夏回族自治区固原地区固原县");
        cityCodes.put("642222","宁夏回族自治区固原地区海原县");
        cityCodes.put("642223","宁夏回族自治区固原地区西吉县");
        cityCodes.put("642224","宁夏回族自治区固原地区隆德县");
        cityCodes.put("642225","宁夏回族自治区固原地区泾源县");
        cityCodes.put("642226","宁夏回族自治区固原地区彭阳县");
        cityCodes.put("650000","新疆维吾尔自治区");
        cityCodes.put("650100","新疆维吾尔族自治区乌鲁木齐市");
        cityCodes.put("650101","新疆维吾尔族自治区乌鲁木齐市市辖区");
        cityCodes.put("650102","新疆维吾尔族自治区乌鲁木齐市天山区");
        cityCodes.put("650103","新疆维吾尔族自治区乌鲁木齐市沙依巴克区");
        cityCodes.put("650104","新疆维吾尔族自治区乌鲁木齐市新市区");
        cityCodes.put("650105","新疆维吾尔族自治区乌鲁木齐市水磨沟区");
        cityCodes.put("650106","新疆维吾尔族自治区乌鲁木齐市头屯河区");
        cityCodes.put("650107","新疆维吾尔族自治区乌鲁木齐市南山矿区");
        cityCodes.put("650108","新疆维吾尔族自治区乌鲁木齐市东山区");
        cityCodes.put("650121","新疆维吾尔族自治区乌鲁木齐市乌鲁木齐县");
        cityCodes.put("650200","新疆维吾尔族自治区克拉玛依市");
        cityCodes.put("650201","新疆维吾尔族自治区克拉玛依市市辖区");
        cityCodes.put("650202","新疆维吾尔族自治区克拉玛依市独山子区");
        cityCodes.put("650203","新疆维吾尔族自治区克拉玛依市克拉玛依区");
        cityCodes.put("650204","新疆维吾尔族自治区克拉玛依市白碱滩区");
        cityCodes.put("650205","新疆维吾尔族自治区克拉玛依市乌尔禾区");
        cityCodes.put("652100","新疆维吾尔族自治区吐鲁番地区");
        cityCodes.put("652101","新疆维吾尔族自治区吐鲁番地区吐鲁番市");
        cityCodes.put("652122","新疆维吾尔族自治区吐鲁番地区鄯善县");
        cityCodes.put("652123","新疆维吾尔族自治区吐鲁番地区托克逊县");
        cityCodes.put("652200","新疆维吾尔族自治区哈密地区");
        cityCodes.put("652201","新疆维吾尔族自治区哈密地区哈密市");
        cityCodes.put("652222","新疆维吾尔族自治区哈密地区巴里坤哈萨克自治县");
        cityCodes.put("652223","新疆维吾尔族自治区哈密地区伊吾县");
        cityCodes.put("652300","新疆维吾尔族自治区昌吉回族自治州");
        cityCodes.put("652301","新疆维吾尔族自治区昌吉回族自治州昌吉市");
        cityCodes.put("652302","新疆维吾尔族自治区昌吉回族自治州阜康市");
        cityCodes.put("652303","新疆维吾尔族自治区昌吉回族自治州米泉市");
        cityCodes.put("652323","新疆维吾尔族自治区昌吉回族自治州呼图壁县");
        cityCodes.put("652324","新疆维吾尔族自治区昌吉回族自治州玛纳斯县");
        cityCodes.put("652325","新疆维吾尔族自治区昌吉回族自治州奇台县");
        cityCodes.put("652327","新疆维吾尔族自治区昌吉回族自治州吉木萨尔县");
        cityCodes.put("652328","新疆维吾尔族自治区昌吉回族自治州木垒哈萨克自治县");
        cityCodes.put("652700","新疆维吾尔族自治区博尔塔拉蒙古自治州");
        cityCodes.put("652701","新疆维吾尔族自治区博尔塔拉蒙古自治州博乐市");
        cityCodes.put("652722","新疆维吾尔族自治区博尔塔拉蒙古自治州精河县");
        cityCodes.put("652723","新疆维吾尔族自治区博尔塔拉蒙古自治州温泉县");
        cityCodes.put("652800","新疆维吾尔族自治区巴音郭楞蒙古自治州");
        cityCodes.put("652801","新疆维吾尔族自治区巴音郭楞蒙古自治州库尔勒市");
        cityCodes.put("652822","新疆维吾尔族自治区巴音郭楞蒙古自治州轮台县");
        cityCodes.put("652823","新疆维吾尔族自治区巴音郭楞蒙古自治州尉犁县");
        cityCodes.put("652824","新疆维吾尔族自治区巴音郭楞蒙古自治州若羌县");
        cityCodes.put("652825","新疆维吾尔族自治区巴音郭楞蒙古自治州且末县");
        cityCodes.put("652826","新疆维吾尔族自治区巴音郭楞蒙古自治州焉耆回族自治县");
        cityCodes.put("652827","新疆维吾尔族自治区巴音郭楞蒙古自治州和静县");
        cityCodes.put("652828","新疆维吾尔族自治区巴音郭楞蒙古自治州和硕县");
        cityCodes.put("652829","新疆维吾尔族自治区巴音郭楞蒙古自治州博湖县");
        cityCodes.put("652900","新疆维吾尔族自治区阿克苏地区");
        cityCodes.put("652901","新疆维吾尔族自治区阿克苏地区阿克苏市");
        cityCodes.put("652922","新疆维吾尔族自治区阿克苏地区温宿县");
        cityCodes.put("652923","新疆维吾尔族自治区阿克苏地区库车县");
        cityCodes.put("652924","新疆维吾尔族自治区阿克苏地区沙雅县");
        cityCodes.put("652925","新疆维吾尔族自治区阿克苏地区新和县");
        cityCodes.put("652926","新疆维吾尔族自治区阿克苏地区拜城县");
        cityCodes.put("652927","新疆维吾尔族自治区阿克苏地区乌什县");
        cityCodes.put("652928","新疆维吾尔族自治区阿克苏地区阿瓦提县");
        cityCodes.put("652929","新疆维吾尔族自治区阿克苏地区柯坪县");
        cityCodes.put("653000","新疆维吾尔族自治区克孜勒苏柯尔克孜自治州");
        cityCodes.put("653001","新疆维吾尔族自治区克孜勒苏柯尔克孜自治州阿图什市");
        cityCodes.put("653022","新疆维吾尔族自治区克孜勒苏柯尔克孜自治州阿克陶县");
        cityCodes.put("653023","新疆维吾尔族自治区克孜勒苏柯尔克孜自治州阿合奇县");
        cityCodes.put("653024","新疆维吾尔族自治区克孜勒苏柯尔克孜自治州乌恰县");
        cityCodes.put("653100","新疆维吾尔族自治区喀什地区");
        cityCodes.put("653101","新疆维吾尔族自治区喀什地区喀什市");
        cityCodes.put("653121","新疆维吾尔族自治区喀什地区疏附县");
        cityCodes.put("653122","新疆维吾尔族自治区喀什地区疏勒县");
        cityCodes.put("653123","新疆维吾尔族自治区喀什地区英吉沙县");
        cityCodes.put("653124","新疆维吾尔族自治区喀什地区泽普县");
        cityCodes.put("653125","新疆维吾尔族自治区喀什地区莎车县");
        cityCodes.put("653126","新疆维吾尔族自治区喀什地区叶城县");
        cityCodes.put("653127","新疆维吾尔族自治区喀什地区麦盖提县");
        cityCodes.put("653128","新疆维吾尔族自治区喀什地区岳普湖县");
        cityCodes.put("653129","新疆维吾尔族自治区喀什地区伽师县");
        cityCodes.put("653130","新疆维吾尔族自治区喀什地区巴楚县");
        cityCodes.put("653131","新疆维吾尔族自治区喀什地区塔什库尔干塔吉克自治县");
        cityCodes.put("653200","新疆维吾尔族自治区和田地区");
        cityCodes.put("653201","新疆维吾尔族自治区和田地区和田市");
        cityCodes.put("653221","新疆维吾尔族自治区和田地区和田县");
        cityCodes.put("653222","新疆维吾尔族自治区和田地区墨玉县");
        cityCodes.put("653223","新疆维吾尔族自治区和田地区皮山县");
        cityCodes.put("653224","新疆维吾尔族自治区和田地区洛浦县");
        cityCodes.put("653225","新疆维吾尔族自治区和田地区策勒县");
        cityCodes.put("653226","新疆维吾尔族自治区和田地区于田县");
        cityCodes.put("653227","新疆维吾尔族自治区和田地区民丰县");
        cityCodes.put("654000","新疆维吾尔族自治区伊犁哈萨克自治州");
        cityCodes.put("654001","新疆维吾尔族自治区伊犁哈萨克自治州奎屯市");
        cityCodes.put("654100","新疆维吾尔族自治区伊犁哈萨克自治州伊犁地区");
        cityCodes.put("654101","新疆维吾尔族自治区伊犁哈萨克自治州伊宁市");
        cityCodes.put("654121","新疆维吾尔族自治区伊犁哈萨克自治州伊宁县");
        cityCodes.put("654122","新疆自治区伊犁哈萨克自治州察布查尔锡伯自治县");
        cityCodes.put("654123","新疆维吾尔族自治区伊犁哈萨克自治州霍城县");
        cityCodes.put("654124","新疆维吾尔族自治区伊犁哈萨克自治州巩留县");
        cityCodes.put("654125","新疆维吾尔族自治区伊犁哈萨克自治州新源县");
        cityCodes.put("654126","新疆维吾尔族自治区伊犁哈萨克自治州昭苏县");
        cityCodes.put("654127","新疆维吾尔族自治区伊犁哈萨克自治州特克斯县");
        cityCodes.put("654128","新疆维吾尔族自治区伊犁哈萨克自治州尼勒克县");
        cityCodes.put("654200","新疆维吾尔族自治区塔城地区");
        cityCodes.put("654201","新疆维吾尔族自治区塔城地区塔城市");
        cityCodes.put("654202","新疆维吾尔族自治区塔城地区乌苏市");
        cityCodes.put("654221","新疆维吾尔族自治区塔城地区额敏县");
        cityCodes.put("654223","新疆维吾尔族自治区塔城地区沙湾县");
        cityCodes.put("654224","新疆维吾尔族自治区塔城地区托里县");
        cityCodes.put("654225","新疆维吾尔族自治区塔城地区裕民县");
        cityCodes.put("654226","新疆维吾尔族自治区塔城地区和布克赛尔蒙古自治县");
        cityCodes.put("654300","新疆维吾尔族自治区阿勒泰地区");
        cityCodes.put("654301","新疆维吾尔族自治区阿勒泰地区阿勒泰市");
        cityCodes.put("654321","新疆维吾尔族自治区阿勒泰地区布尔津县");
        cityCodes.put("654322","新疆维吾尔族自治区阿勒泰地区富蕴县");
        cityCodes.put("654323","新疆维吾尔族自治区阿勒泰地区福海县");
        cityCodes.put("654324","新疆维吾尔族自治区阿勒泰地区哈巴河县");
        cityCodes.put("654325","新疆维吾尔族自治区阿勒泰地区青河县");
        cityCodes.put("654326","新疆维吾尔族自治区阿勒泰地区吉木乃县");
        cityCodes.put("659000","新疆维吾尔族自治区直辖县级行政单位");
        cityCodes.put("659001","新疆维吾尔族自治区石河子市");
    }    
    
    /**
     * 省，直辖市代码表： { 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
     * 21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",
     * 33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",
     * 42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",
     * 51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",
     * 63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}
     */
    protected String codeAndProvince[][] = { { "11", "北京" }, { "12", "天津" }, { "13", "河北" }, { "14", "山西" },
            { "15", "内蒙古" }, { "21", "辽宁" }, { "22", "吉林" }, { "23", "黑龙江" }, { "31", "上海" }, { "32", "江苏" },
            { "33", "浙江" }, { "34", "安徽" }, { "35", "福建" }, { "36", "江西" }, { "37", "山东" }, { "41", "河南" },
            { "42", "湖北" }, { "43", "湖南" }, { "44", "广东" }, { "45", "广西" }, { "46", "海南" }, { "50", "重庆" },
            { "51", "四川" }, { "52", "贵州" }, { "53", "云南" }, { "54", "西藏" }, { "61", "陕西" }, { "62", "甘肃" },
            { "63", "青海" }, { "64", "宁夏" }, { "65", "新疆" }, { "71", "台湾" }, { "81", "香港" }, { "82", "澳门" },
            { "91", "国外" } };
            
    private static String provinceCode[] = { "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35",
            "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65",
            "71", "81", "82", "91" };
            
    // 每位加权因子
    private static int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
    
    // 第18位校检码
    private String verifyCode[] = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
    
    /** 中国公民身份证号码最小长度。 */
    public static final int CHINA_ID_MIN_LENGTH = 15;
    
    /** 中国公民身份证号码最大长度。 */
    public static final int CHINA_ID_MAX_LENGTH = 18;
    
    /**
     * 验证所有的身份证的合法性
     * 
     * @param idCard
     * @return
     */
    public static boolean validatedIDCards(String idCard) {
        idCard = idCard.trim();
        if (idCard.length() == CHINA_ID_MIN_LENGTH) {
            return isValidated15IDCard(idCard);
        }else if (idCard.length() == CHINA_ID_MAX_LENGTH) {
            return isValidated18IDCard(idCard);
        }else{
            //港澳台.
            String[] cardval = validatedOtherIDCard(idCard);
            if (cardval != null) {
                if (cardval[2].equals("true")) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * <p>
     * 判断18位身份证的合法性
     * </p>
     * 根据〖中华人民共和国国家标准GB11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
     * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
     * <p>
     * 顺序码: 表示在同一地址码所标识的区域范围内，对同年、同月、同 日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配 给女性。
     * </p>
     * <p>
     * 1.前1、2位数字表示：所在省份的代码； 2.第3、4位数字表示：所在城市的代码； 3.第5、6位数字表示：所在区县的代码；
     * 4.第7~14位数字表示：出生年、月、日； 5.第15、16位数字表示：所在地的派出所的代码；
     * 6.第17位数字表示性别：奇数表示男性，偶数表示女性；
     * 7.第18位数字是校检码：也有的说是个人信息码，一般是随计算机的随机产生，用来检验身份证的正确性。校检码可以是0~9的数字，有时也用x表示。
     * </p>
     * <p>
     * 第十八位数字(校验码)的计算方法为： 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4
     * 2 1 6 3 7 9 10 5 8 4 2
     * </p>
     * <p>
     * 2.将这17位数字和系数相乘的结果相加。
     * </p>
     * <p>
     * 3.用加出来和除以11，看余数是多少？
     * </p>
     * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3
     * 2。
     * <p>
     * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
     * </p>
     * 
     * @param idCard
     * @return
     */
    public static boolean isValidated18IDCard(String idCard) {
        // 非18位为假
        if (idCard.length() != 18) {
            return false;
        }
        // 获取前17位
        String idCard17 = idCard.substring(0, 17);
        // 获取第18位
        String idCard18Code = idCard.substring(17, 18);
        char c[] = null;
        String checkCode = "";
        // 是否都为数字
        if (isDigital(idCard17)) {
            c = idCard17.toCharArray();
        }
        else {
            return false;
        }
        
        if (null != c) {
            int bit[] = new int[idCard17.length()];
            bit = converCharToInt(c);
            int sum17 = 0;
            sum17 = getPowerSum(bit);
            // 将和值与11取模得到余数进行校验码判断
            checkCode = getCheckCodeBySum(sum17);
            if (null == checkCode) {
                return false;
            }
            // 将身份证的第18位与算出来的校码进行匹配，不相等就为假
            if (!idCard18Code.equalsIgnoreCase(checkCode)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 验证15位身份证的合法性,该方法验证不准确，最好是将15转为18位后再判断，该类中已提供。
     * 
     * @param idCard
     * @return
     */
    public static boolean isValidated15IDCard(String idCard) {
        // 非15位为假
        if (idCard.length() != 15) {
            return false;
        }
        // 是否全都为数字
        if (isDigital(idCard)) {
            String provinceid = idCard.substring(0, 2);
            String birthday = idCard.substring(6, 12);
            int year = Integer.parseInt(idCard.substring(6, 8));
            int month = Integer.parseInt(idCard.substring(8, 10));
            int day = Integer.parseInt(idCard.substring(10, 12));
            // 判断是否为合法的省份
            boolean flag = false;
            for (String id : provinceCode) {
                if (id.equals(provinceid)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
            // 该身份证生出日期在当前日期之后时为假
            Date birthdate = null;
            try {
                birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            if (birthdate == null || new Date().before(birthdate)) {
                return false;
            }
            // 判断是否为合法的年份
            GregorianCalendar curDay = new GregorianCalendar();
            int curYear = curDay.get(Calendar.YEAR);
            int year2bit = Integer.parseInt(String.valueOf(curYear).substring(2));
            // 判断该年份的两位表示法，小于50的和大于当前年份的，为假
            if ((year < 50 && year > year2bit)) {
                return false;
            }
            // 判断是否为合法的月份
            if (month < 1 || month > 12) {
                return false;
            }
            // 判断是否为合法的日期
            boolean mflag = false;
            curDay.setTime(birthdate);  // 将该身份证的出生日期赋于对象curDay
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    mflag = (day >= 1 && day <= 31);
                    break;
                case 2: // 公历的2月非闰年有28天,闰年的2月是29天。
                    if (curDay.isLeapYear(curDay.get(Calendar.YEAR))) {
                        mflag = (day >= 1 && day <= 29);
                    }
                    else {
                        mflag = (day >= 1 && day <= 28);
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    mflag = (day >= 1 && day <= 30);
                    break;
            }
            if (!mflag) {
                return false;
            }
        }
        else {
            return false;
        }
        return true;
    }
    
    /**
     * 验证10位身份编码是否合法
     * 
     * @param idCard 身份编码
     * @return 身份证信息数组
     *         <p>
     *         [0] - 台湾、澳门、香港 [1] - 性别(男M,女F,未知N) [2] - 是否合法(合法true,不合法false)
     *         若不是身份证件号码则返回null
     *         </p>
     */
    public static String[] validatedOtherIDCard(String idCard) {
        String[] info = new String[3];
        String card = idCard.replaceAll("[\\(|\\)]", "");
        if (card.length() != 8 && card.length() != 9 && idCard.length() != 10) {
            return null;
        }
        if (idCard.matches("^[a-zA-Z][0-9]{9}$")) { // 台湾
            info[0] = "台湾";
            System.out.println("11111");
            String char2 = idCard.substring(1, 2);
            if (char2.equals("1")) {
                info[1] = "M";
                System.out.println("MMMMMMM");
            } else if (char2.equals("2")) {
                info[1] = "F";
                System.out.println("FFFFFFF");
            } else {
                info[1] = "N";
                info[2] = "false";
                System.out.println("NNNN");
                return info;
            }
            info[2] = validatedTWCard(idCard) ? "true" : "false";
        } else if (idCard.matches("^[1|5|7][0-9]{6}\\(?[0-9A-Z]\\)?$")) { // 澳门
            info[0] = "澳门";
            info[1] = "N";
            // TODO
        } else if (idCard.matches("^[A-Z]{1,2}[0-9]{6}\\(?[0-9A]\\)?$")) { // 香港
            info[0] = "香港";
            info[1] = "N";
            info[2] = validatedHKCard(idCard) ? "true" : "false";
        } else {
            return null;
        }
        return info;
    }
    
    
    /**
     * 验证台湾身份证号码
     * 
     * @param idCard
     *            身份证号码
     * @return 验证码是否符合
     */
    public static boolean validatedTWCard(String idCard) {
        String start = idCard.substring(0, 1);
        String mid = idCard.substring(1, 9);
        String end = idCard.substring(9, 10);
        Integer iStart = twFirstCode.get(start);
        Integer sum = iStart / 10 + (iStart % 10) * 9;
        char[] chars = mid.toCharArray();
        Integer iflag = 8;
        for (char c : chars) {
            sum = sum + Integer.valueOf(c + "") * iflag;
            iflag--;
        }
        return (sum % 10 == 0 ? 0 : (10 - sum % 10)) == Integer.valueOf(end) ? true : false;
    }
 
    /**
     * 验证香港身份证号码(存在Bug，部份特殊身份证无法检查)
     * <p>
     * 身份证前2位为英文字符，如果只出现一个英文字符则表示第一位是空格，对应数字58 前2位英文字符A-Z分别对应数字10-35
     * 最后一位校验码为0-9的数字加上字符"A"，"A"代表10
     * </p>
     * <p>
     * 将身份证号码全部转换为数字，分别对应乘9-1相加的总和，整除11则证件号码有效
     * </p>
     * 
     * @param idCard 身份证号码
     * @return 验证码是否符合
     */
    public static boolean validatedHKCard(String idCard) {
        String card = idCard.replaceAll("[\\(|\\)]", "");
        Integer sum = 0;
        if (card.length() == 9) {
            sum = (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 9
                    + (Integer.valueOf(card.substring(1, 2).toUpperCase().toCharArray()[0]) - 55) * 8;
            card = card.substring(1, 9);
        } else {
            sum = 522 + (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 8;
        }
        String mid = card.substring(1, 7);
        String end = card.substring(7, 8);
        char[] chars = mid.toCharArray();
        Integer iflag = 7;
        for (char c : chars) {
            sum = sum + Integer.valueOf(c + "") * iflag;
            iflag--;
        }
        if (end.toUpperCase().equals("A")) {
            sum = sum + 10;
        } else {
            sum = sum + Integer.valueOf(end);
        }
        return (sum % 11 == 0) ? true : false;
    }
 
    
    /**
     * 将15位的身份证转成18位身份证
     * 
     * @param idCard
     * @return
     */
    public static String conver15CardTo18(String idCard) {
        String idCard17 = null;
        // 非15位身份证
        if (idCard.length() != CHINA_ID_MIN_LENGTH) {
            return null;
        }
        if (isDigital(idCard)) {
            // 获取出生年月日
            String birthday = idCard.substring(6, 12);
            Date birthdate = null;
            try {
                birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cday = Calendar.getInstance();
            cday.setTime(birthdate);
            String year = String.valueOf(cday.get(Calendar.YEAR));
            idCard17 = idCard.substring(0, 6) + year + idCard.substring(8);
            char c[] = idCard17.toCharArray();
            String checkCode = "";
            if (null != c) {
                int bit[] = new int[idCard17.length()];
                // 将字符数组转为整型数组
                bit = converCharToInt(c);
                int sum17 = 0;
                sum17 = getPowerSum(bit);
                // 获取和值与11取模得到余数进行校验码
                checkCode = getCheckCodeBySum(sum17);
                // 获取不到校验位
                if (null == checkCode) {
                    return null;
                }
                // 将前17位与第18位校验码拼接
                idCard17 += checkCode;
            }
        }
        else { // 身份证包含数字
            return null;
        }
        return idCard17;
    }
    
    /**
     * 根据身份编号获取年龄
     * 
     * @param idCard
     *            身份编号
     * @return 年龄
     */
    public static int getAgeByIDCard(String idCard) {
        int iAge = 0;
        if (idCard.length() == CHINA_ID_MIN_LENGTH) {
            idCard = conver15CardTo18(idCard);
        }
        String year = idCard.substring(6, 10);
        Calendar cal = Calendar.getInstance();
        int iCurrYear = cal.get(Calendar.YEAR);
        iAge = iCurrYear - Integer.valueOf(year);
        return iAge;
    }
 
    /**
     * 根据身份编号获取生日
     * 
     * @param idCard 身份编号
     * @return 生日(yyyyMMdd)
     */
    public static String getBirthByIDCard(String idCard) {
        Integer len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            return null;
        } else if (len == CHINA_ID_MIN_LENGTH) {
            idCard = conver15CardTo18(idCard);
        }
        return idCard.substring(6, 14);
    }
 
    /**
     * 根据身份编号获取生日年
     * 
     * @param idCard 身份编号
     * @return 生日(yyyy)
     */
    public static Short getYearByIDCard(String idCard) {
        Integer len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            return null;
        } else if (len == CHINA_ID_MIN_LENGTH) {
            idCard = conver15CardTo18(idCard);
        }
        return Short.valueOf(idCard.substring(6, 10));
    }
 
    /**
     * 根据身份编号获取生日月
     * 
     * @param idCard
     *            身份编号
     * @return 生日(MM)
     */
    public static Short getMonthByIDCard(String idCard) {
        Integer len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            return null;
        } else if (len == CHINA_ID_MIN_LENGTH) {
            idCard = conver15CardTo18(idCard);
        }
        return Short.valueOf(idCard.substring(10, 12));
    }
 
    /**
     * 根据身份编号获取生日天
     * 
     * @param idCard
     *            身份编号
     * @return 生日(dd)
     */
    public static Short getDateByIDCard(String idCard) {
        Integer len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            return null;
        } else if (len == CHINA_ID_MIN_LENGTH) {
            idCard = conver15CardTo18(idCard);
        }
        return Short.valueOf(idCard.substring(12, 14));
    }
 
    /**
     * 根据身份编号获取性别
     * 
     * @param idCard 身份编号
     * @return 性别(M-男，F-女，N-未知)
     */
    public static String getGenderByIDCard(String idCard) {
        String sGender = "N";
        if (idCard.length() == CHINA_ID_MIN_LENGTH) {
            idCard = conver15CardTo18(idCard);
        }
        String sCardNum = idCard.substring(16, 17);
        if (Integer.parseInt(sCardNum) % 2 != 0) {
            sGender = "M";
        } else {
            sGender = "F";
        }
        return sGender;
    }
 
    /**
     * 根据身份编号获取户籍省份
     * 
     * @param idCard 身份编码
     * @return 省级编码。
     */
    public static String getProvinceByIDCard(String idCard) {
        int len = idCard.length();
        String sProvince = null;
        String sProvinNum = "";
        if (len == CHINA_ID_MIN_LENGTH || len == CHINA_ID_MAX_LENGTH) {
            sProvinNum = idCard.substring(0, 2);
        }
        sProvince = provinceCodes.get(sProvinNum);
        return sProvince;
    }
 
    
    /**
     * 根据身份编号获取户籍省份ID
     * 
     * @param idCard 身份编码
     * @return 省级编码。
     */
    public static String getProvinceIDByIDCard(String idCard) {
        int len = idCard.length();
        String sProvince = null;
        if (len == CHINA_ID_MIN_LENGTH || len == CHINA_ID_MAX_LENGTH) {
            return idCard.substring(0, 2);
        }else {
            return sProvince;
        }
    }
    
    /**
     * 根据身份编号获取户籍市.县.区
     * 
     * @param idCard 身份编码
     * @return 户籍市.县.区。
     */
    public static String getCityByIDCard(String idCard) {
        int len = idCard.length();
        String city = null;
        String cityNum = "";
        if (len == CHINA_ID_MIN_LENGTH || len == CHINA_ID_MAX_LENGTH) {
        	cityNum = idCard.substring(0, 6);
        }
        city = cityCodes.get(cityNum);
        if (StringUtils.isEmpty(city)) {
			city = getCityLocal(idCard);
		}
        return city;
    }
 
    /**
     * 网络请求获取.
     * @param idCard
     * @return
     */
    public static String getCityLocal(String idCard) {
    	try {
    		String uri = "http://qq.ip138.com/idsearch/index.asp?action=idcard&userid=" + idCard;
    		URL url = new URL(uri);
    		URLConnection urlConnection = url.openConnection();
    		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "GBK"));
    		String str = "";
    		StringBuffer contents = new StringBuffer();
    		while ((str = br.readLine()) != null) {
    			contents.append(str);
    		}
    		br.close();
    		String regx1 = "<td class=\"tdc2\">(.*?)</td>";
    		Pattern p = Pattern.compile(regx1);
    		String text = contents.toString();
    		Matcher macher = p.matcher(text);
    		String cityLocal = "";
    		while (macher.find()) {
    			cityLocal = macher.group(1).trim().toString();
    		}
    		cityLocal = cityLocal.replaceAll("<br/>", "").replaceAll(" ", "-");
    		return cityLocal;
		}
		catch (Exception e) {
			return "";
		}
	}
    
    /**
     * 15位和18位身份证号码的基本数字和位数验校
     * 
     * @param idCard
     * @return
     */
    public boolean isIDCard(String idCard) {
        return idCard == null || "".equals(idCard) ? false
                : Pattern.matches("(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)", idCard);
    }
    
    /**
     * 15位身份证号码的基本数字和位数验校
     * 
     * @param idCard
     * @return
     */
    public boolean is15IDCard(String idCard) {
        return idCard == null || "".equals(idCard) ? false
                : Pattern.matches("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$", idCard);
    }
    
    /**
     * 18位身份证号码的基本数字和位数验校
     * 
     * @param idCard
     * @return
     */
    public boolean is18IDCard(String idCard) {
        return Pattern.matches("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$",
                idCard);
    }
    
    /**
     * 数字验证
     * 
     * @param str
     * @return
     */
    public static boolean isDigital(String str) {
        return str == null || "".equals(str) ? false : str.matches("^[0-9]*$");
    }
    
    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     * 
     * @param bit
     * @return
     */
    public static int getPowerSum(int[] bit) {
        int sum = 0;
        if (power.length != bit.length) {
            return sum;
        }
        for (int i = 0; i < bit.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    sum = sum + bit[i] * power[j];
                }
            }
        }
        return sum;
    }
    
    /**
     * 将和值与11取模得到余数进行校验码判断
     * 
     * @param checkCode
     * @param sum17
     * @return 校验位
     */
    public static String getCheckCodeBySum(int sum17) {
        String checkCode = null;
        switch (sum17 % 11) {
            case 10:
                checkCode = "2";
                break;
            case 9:
                checkCode = "3";
                break;
            case 8:
                checkCode = "4";
                break;
            case 7:
                checkCode = "5";
                break;
            case 6:
                checkCode = "6";
                break;
            case 5:
                checkCode = "7";
                break;
            case 4:
                checkCode = "8";
                break;
            case 3:
                checkCode = "9";
                break;
            case 2:
                checkCode = "x";
                break;
            case 1:
                checkCode = "0";
                break;
            case 0:
                checkCode = "1";
                break;
        }
        return checkCode;
    }
    
    /**
     * 将字符数组转为整型数组
     * 
     * @param c
     * @return
     * @throws NumberFormatException
     */
    public static int[] converCharToInt(char[] c) throws NumberFormatException {
        int[] a = new int[c.length];
        int k = 0;
        for (char temp : c) {
            a[k++] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }
 
}
