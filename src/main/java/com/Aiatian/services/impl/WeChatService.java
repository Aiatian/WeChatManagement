package com.Aiatian.services.impl;

import com.Aiatian.TulingRobot.WechatProcess;
import com.Aiatian.services.IWeChatService;
import com.Aiatian.util.WechatUtil;
import com.Aiatian.util.entity.*;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service("weChatService")
public class WeChatService implements IWeChatService {

    private  static  final String TOKEN = "Aiatian";

    private  static  final  String GET_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    private  static  final String APPID="wxa4e87c8a956c6cef"; // 正式 wxb8f2515ffd919ad7

    private  static  final String APPSECRET="f9e04c9acc538330d18a27350003be59";  //6f23203a9a6d5ae8948d81ae7df18af3

    private  static AccessToken at;

    private    static  void getToken(){
        String url = GET_TOKEN_URL.replace("APPID",APPID).replace("APPSECRET",APPSECRET);
        String tokenStr = WechatUtil.get(url);

        JSONObject jsonObject = new JSONObject(tokenStr);

        System.out.println(jsonObject);

        String access_token = jsonObject.getString("access_token");
        int expires_in = jsonObject.getInt("expires_in");

        at = new AccessToken(access_token,String.valueOf(expires_in));



    }

    public  static  String getAccessToken(){
        if (at ==null || at.inExpired()){
            getToken();
        }
        return  at.getAccessToken();
    }

    /**
     * 验证签名
     * @param timestamp  时间戳
     * @param nonce      随机数
     * @param signature  微信加密签名
     * @return
     * By：Aiatian
     */
    public static boolean check(String timestamp, String nonce, String signature) {
        // 1）将token、timestamp、nonce三个参数进行字典序排序
        String[] strs = new String[]{TOKEN,timestamp,nonce};
        Arrays.sort(strs);
        // 2）将三个参数字符串拼接成一个字符串进行sha1加密
        String str = strs[0]+strs[1]+strs[2];
        String mysig = sha1(str);

        System.out.println(mysig);
        System.out.println(signature);
        // 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信

        return mysig.equalsIgnoreCase(signature);
    }



    /**
     *  进行 sha1 加密
     * @param str
     * @return
     */
    private static String sha1(String str) {
        try {
            // 获取一个加密对象
            MessageDigest md = MessageDigest.getInstance("sha1");
            //加密
            byte[] digest = md.digest(str.getBytes());
            // 处理加密结果
            char [] chars ={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(chars[(b>>4)&15]);
                sb.append(chars[b&15]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * By: Aiatian
     * QQ:1924734429
     *
     * 获取xml后解析 根节点
     * @Description //TODO
     * @Date
     * @Param  * @param null
     * @return
     **/
    public Map<String,String> parseRequest(InputStream is) {
        SAXReader reader = new SAXReader();

        Map<String,String> map = new HashMap<>();
        try {
            // 读取输入流。获取文档对象 
            Document document = reader.read(is);
            //更具文档对象获取根节点
            Element root = document.getRootElement();
            // 获取根节点的 所以子节点
            List<Element> elements = root.elements();

            for (Element element : elements) {
                map.put(element.getName(),element.getStringValue());
            }


        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return map;

    }


    /**
     * By: Aiatian
     * QQ:1924734429
     *
     * 判断输入的数据是什么类型
     * 然后给与回复
     * @Description //TODO
     * @Date
     * @Param  * @param null
     * @return
     **/
    public  static String getRespose(Map<String, String> requserMap) {
        String msgType = requserMap.get("MsgType");

        BaseMessage msg = null;
        switch (msgType){
            case "text":
                // 模糊 关键字requserMap.get("Content").matches(".*新闻.*"
                if (requserMap.get("Content").equals("图文")){
                    msg = xinWenMesage(requserMap);
                }else{
                    // 图灵机器人智能回复
                    msg = tuLingTextMessage(requserMap);
                }



                break;

            case "image":

                break;

            case "voice":

                break;

            case "video":

                break;

            case "shortvideo":

                break;

            case "location":

                break;

            case "link":

                break;

            case "event":
                msg = deallEvent(requserMap);
                break;

            default:
                break;
        }

        //System.out.println(msg);
        if (msg!=null){
            return beanToXml(msg);
        }else {
            return null;
        }
    }

    private static BaseMessage xinWenMesage(Map<String, String> requserMap) {
        List<Article> list = new ArrayList<>();
        Article article = new Article("图文消息","由于最新的微信公众，图文推送样式改版所以成为了这个样子。 详细去看官网","https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1657991340,2883342440&fm=26&gp=0.jpg","https://developers.weixin.qq.com/community/develop/doc/000aa042554f68a624e708bb556800");


        list.add(article);
        NewsMessage tm = new NewsMessage(requserMap,list);



        return tm;

    }

    private static BaseMessage xinWenMesages(Map<String, String> requserMap) {
        String zhaohuaxishiText="经为流逝的日月。纬为人们的生业。住在远离尘嚣的土地，一边将每天的事情编织成名为希比奥尔的布，一边静静生活的伊奥鲁夫人民。在15岁左右外表就停止成长，拥有数百年寿命的他们，被称为“离别一族”，并被视为活着的传说。没有双亲的伊奥鲁夫少女玛奇亚，过着被伙伴包围的平稳日子，却总感觉“孤身一人”。他们的这种日常，一瞬间就崩溃消失。追求伊奥鲁夫的长寿之血，梅萨蒂军乘坐着名为雷纳特的古代兽发动了进攻。在绝望与混乱之中，伊奥鲁夫的第一美女蕾莉亚被梅萨蒂带走，而玛奇亚暗恋的少年克里姆也失踪了。玛奇亚虽然总算逃脱了，却失去了伙伴和归去之地……玛奇亚怀着空虚的心灵在黑暗的森林中徘徊。如同被召唤一般，她在那里遇到了刚刚失去双亲的“孤身一人”的婴儿。逐渐成长为少年的艾瑞尔。即使时间流逝也仍然保持少女样貌的玛奇亚。在同样的季节中，经过着不同的时间。在变化的时代当中，两人的牵绊逐渐变化着色调——。两名孤身一人之人的相遇，编织出无可替代的时间的故事。";
        String xiamuText="每天在人与妖怪之间过着忙碌日子的夏目，偶然与过去的同学・结城重逢了，以此想起了与妖怪有关的苦涩记忆。此时，夏目认识了在归还名字的妖怪记忆中出现的女性·津村容莉枝。知晓玲子的她，现在和独子椋雄一起平静地生活着。夏目通过和他们交流，心里也变得平静下来。但是这对母子所居住的城镇，似乎潜藏着神秘的妖怪。在调查此事回来的路上，寄生于猫咪老师身体上的“妖之种”，在藤原家的庭院中，一夜之间就长成树结出果实。不知为什么，吃掉了与自己形状相似果实的猫咪老师，竟然分裂成了3个？";
        String qianyuqianxunText="10岁的少女千寻与父母一起从都市搬家到了乡下。没想到在搬家的途中，一家人发生了意外。他们进入了汤屋老板魔女控制的奇特世界——在那里不劳动的人将会被变成动物。千寻的爸爸妈妈因贪吃变成了猪，千寻为了救爸爸妈妈经历了很多磨难，在期间她遇见了白龙，一个既聪明又冷酷的少年，在经历了很多事情之后，千寻最后救出了爸爸妈妈，拯救了白龙";
        String tianqizhiziText="“很想试试在那光芒之中前行！”\n" +
                "高一的夏天，少年帆高离家出走，一个人来到东京。帆高好不容易找到一份工，为一本古怪的“神秘学杂志”写稿，生活孤单贫苦。连日的滂沱大雨，像是来映衬他的失落。在纷纭杂沓的大都会一角，帆高遇上了一个可爱少女阳菜。阳菜和弟弟相依为命，个性坚强、开朗，但她心中隐藏着一个重大秘密──每当她说：“现在开始天晴了！”雨便渐渐停下来，美丽的阳光洒落街上。原来，她拥有一股不可思议的能量，一股能让天空放晴的异力……";
        List<Article> list = new ArrayList<>();
        Article article = new Article("朝花夕誓——于离别之朝束起约定之花",zhaohuaxishiText,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561203690595&di=089fdb2e88688ea3802009f90416686b&imgtype=0&src=http%3A%2F%2Fimages.china.cn%2Fsite1007%2F2019-02%2F18%2Fe18d4d63-6310-4efe-8fb7-71e5e9335e27.jpg","https://baike.baidu.com/item/%E6%9C%9D%E8%8A%B1%E5%A4%95%E8%AA%93%E2%80%94%E2%80%94%E4%BA%8E%E7%A6%BB%E5%88%AB%E4%B9%8B%E6%9C%9D%E6%9D%9F%E8%B5%B7%E7%BA%A6%E5%AE%9A%E4%B9%8B%E8%8A%B1/23285005?fromtitle=%E6%9C%9D%E8%8A%B1%E5%A4%95%E8%AA%93&fromid=23292439&fr=aladdin");
        Article article2 = new Article("夏目友人帐 ～缘结空蝉～",xiamuText,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561203615897&di=9be1818822c4a12edfcb21b5d3bf2edb&imgtype=0&src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2Fee175b7311406da8731601b332fc48688bceb995.jpg","https://baike.baidu.com/item/%E5%A4%8F%E7%9B%AE%E5%8F%8B%E4%BA%BA%E5%B8%90%20%EF%BD%9E%E7%BC%98%E7%BB%93%E7%A9%BA%E8%9D%89%EF%BD%9E/22605371?fr=aladdin");
        Article article3= new Article("千与千寻",qianyuqianxunText,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561203808787&di=1292ae151ad4feba9e19fe59c05c3cfd&imgtype=0&src=http%3A%2F%2Fpics0.baidu.com%2Ffeed%2F0df3d7ca7bcb0a46da73f1dda37ef2216a60afa7.jpeg%3Ftoken%3D7b5dd9a23aeb2c495a504fb5110cd087%26s%3D18B37F950813EED61A0C557D0300303A","https://baike.baidu.com/item/%E5%8D%83%E4%B8%8E%E5%8D%83%E5%AF%BB/389041?fr=aladdin");
        Article article4 = new Article("天气之子",tianqizhiziText,"http://pic.topys.cn/editor/20181215/1677886052.jpg?x-oss-process=style/article-content-pic-1x","https://baike.baidu.com/item/%E5%A4%A9%E6%B0%94%E4%B9%8B%E5%AD%90/23203347?fr=aladdin");


        list.add(article);
        list.add(article2);
        list.add(article3);
        list.add(article4);
        NewsMessage tm = new NewsMessage(requserMap,list);



        return tm;

    }


    private static String beanToXml(BaseMessage msg) {

        XStream stream = new XStream();
        stream.processAnnotations(TextMessage.class);
        stream.processAnnotations(ImageMessage.class);
        stream.processAnnotations(MusicMessage.class);
        stream.processAnnotations(VideoMessage.class);
        stream.processAnnotations(VoiceMessage.class);
        stream.processAnnotations(NewsMessage.class);
        String xml= stream.toXML(msg);
        return xml;
    }


    /**
     * By: Aiatian
     * QQ:1924734429
     *
     * 图灵机器人
     * @Description //TODO
     * @Date
     * @Param  * @param null
     * @return
     **/
    private static BaseMessage tuLingTextMessage(Map<String, String> requserMap) {

        // 获取 用户的输入
        String quesiton = requserMap.get("Content");

        WechatProcess wcp = new WechatProcess();

        // 拿用户输入 发送给图灵机器人 然后返回 回复 在发送给用户
        String Text = wcp.Chat("" + quesiton);
        TextMessage tm = new TextMessage(requserMap,Text);

        return tm;
    }

    private  static  BaseMessage deallEvent(Map<String,String> requestMap){
        String event = requestMap.get("Event");
        switch (event){
            case"CLICK":
                    return dealClick(requestMap);


            case"VIEW":
                    return dealView(requestMap);


            case"":

                break;


            default:
                break;


        }


        return null;
    }

    private static BaseMessage dealView(Map<String, String> requestMap) {

        return null;

    }

    private static BaseMessage dealClick(Map<String, String> requestMap) {
        String key = requestMap.get("EventKey");

        switch (key){
            case "1":
                return xinWenMesages(requestMap);

            case "32":
                return  new TextMessage(requestMap,"你好啊");

                default:

                    break;
        }

        return null;
    }
}
