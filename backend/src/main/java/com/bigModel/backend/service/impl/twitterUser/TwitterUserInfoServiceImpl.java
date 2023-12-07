package com.bigModel.backend.service.impl.twitterUser;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigModel.backend.mapper.TwitterUserInfoMapper;
import com.bigModel.backend.pojo.TwitterUser;
import com.bigModel.backend.service.twitterUser.TwitterUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TwitterUserInfoServiceImpl implements TwitterUserInfoService {

    @Autowired
    private TwitterUserInfoMapper infoMapper;
    @Override
    public void addTwitterUser(TwitterUser twitterUser) {
        infoMapper.insert(twitterUser);
    }

    @Override
    public IPage<TwitterUser> getTwitterUsers(Integer pageNum) {
        IPage<TwitterUser> page = new Page<>(pageNum, 10);
        QueryWrapper<TwitterUser> queryWrapper = new QueryWrapper<>();
        IPage<TwitterUser> pageList = infoMapper.selectPage(page, queryWrapper);

        Long count = infoMapper.selectCount(null);
        Map<String, Object> map = new HashMap<>();
        map.put("data", pageList.getRecords());
        map.put("current", String.valueOf(pageList.getCurrent()));
        map.put("size", String.valueOf(pageList.getSize()));
        map.put("pages", String.valueOf(pageList.getPages()));
        map.put("total_users", count);
        return pageList;
    }

    @Override
    public void deleteTwitterUserByTwitterId(String twitterId) {
        QueryWrapper<TwitterUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("twitter_id", twitterId);
        infoMapper.delete(queryWrapper);
    }
}

//
// "yingzhili",
// "liqiaochu01",
// "azurewaylee",
// "709liwenzu",
// "liangxiaojun",
// "lqllawyer",
// "liudimouse",
// "zhaiyanmin2017",
// "liu_juefan",
// "liujunning",
// "suliliu",
// "3OoCglQGen9JSyJ",
// "luoshch",
// "malaogui",
// "MaoYuShi",
// "niyulan2013",
// "puzhiqiang",
// "zhi_pu2329",
// "qizhiyong",
// "sarah_chainaBJ",
// "rongjian1957",
// "ZT9I7yE015ljZg0",
// "flourishflood",
// "ja5MeSG3rWPpkzG",
// "sunliyong8964",
// "tengbiao",
// "wangdan1989",
// "wanggongquan007",
// "wangjinbo",
// "juntaowang",
// "wlixiong",
// "wlh8964",
// "709wangqiaoling",
// "quanzhangl7193",
// "wlixiong",
// "WEI_JINGSHENG",
// "wuerkaixi",
// "ww01bj",
// "wuzuolai",
// "xialinlawyer",
// "lawyxieyanyier",
// "xuxin1970",
// "bjxuyonghai",
// "xuyan709",
// "zhiyongxu",
// "yanzhengxue1944",
// "yanwenxin81",
// "_xiaohan",
// "yuwensheng9",
// "okokyea",
// "ShengXue_ca",
// "zhaiyanmin",
// "zhangbaoch93033",
// "qingshilvshi",
// "zhangming1",
// "qianfanzhang",
// "zhanglifan",
// "utmR6mn7kHsbc47",
// "ZhouFengSuo",
// "lee91741",
// "VVGHAxQdlDZnF6Y",
// "jhf8964",
// "dayangelcp",
// "aiww",
// "HuPing1",
// "baolongjun2015",
// "wangyuLawer",
// "yuhulawyer",
// "wenyunchao",
// "jiangty02",
// "JingzhouTao",