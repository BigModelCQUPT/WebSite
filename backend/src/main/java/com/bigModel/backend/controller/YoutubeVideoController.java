package com.bigModel.backend.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigModel.backend.advice.result.Result;
import com.bigModel.backend.mapper.YoutubeVideoMapper;
import com.bigModel.backend.pojo.Tweet;
import com.bigModel.backend.pojo.YoutubeUser;
import com.bigModel.backend.pojo.YoutubeVideo;
import com.bigModel.backend.service.YoutubeUserService;
import com.bigModel.backend.service.YoutubeVideoService;
import com.bigModel.backend.utils.YoutubeVideoUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/youtube")
public class YoutubeVideoController {

    @Autowired
    private YoutubeVideoService youtubeVideoService;

    @Autowired
    private YoutubeUserService youtubeUserService;

    @GetMapping("/update")
    public void updateVideo() throws IOException {
        List<YoutubeUser> userList = youtubeUserService.listAll();
        List<YoutubeVideo> list = YoutubeVideoUtil.getVideo(userList);
        for (int i = 0; i < list.size(); i ++) {
            youtubeVideoService.addVideo(list.get(i));
        }
    }

    @PostMapping("/listAll/{page}/{size}")
    public IPage<YoutubeVideo> listAll(@PathVariable(value = "page") Integer page,
                                       @PathVariable(value = "size") Integer size) {
        return youtubeVideoService.listAll(page, size);
    }

    @PostMapping("/readYoutube")
    public Result readTweet(@RequestBody List<YoutubeVideo> data){
//        List<Tweet> needReadList = castList(data.get("needReadList"), Tweet.class);
        for (YoutubeVideo youtubeVideo : data) {
            Integer id = youtubeVideo.getId();
            youtubeVideoService.updateFlag(id);
        }
        return Result.success("修改成功");
    }

    @GetMapping("/export")
    public void exportHistory(@RequestParam(required = false) String ids, HttpServletResponse httpServletResponse) throws Exception {
        if (StringUtils.isNotBlank(ids)) {
            List<Integer> needExportIds = Arrays.stream(ids.split(",")).map(Integer::valueOf).collect(Collectors.toList());
            List<YoutubeVideo> listMessage = youtubeVideoService.listNeedExportIds(needExportIds);
            EasyExcelFactory.write(httpServletResponse.getOutputStream(), YoutubeVideo.class).sheet("youtube").doWrite(listMessage);
        } else {
            List<YoutubeVideo> listMessage = youtubeVideoService.listAllExportIds();
            EasyExcelFactory.write(httpServletResponse.getOutputStream(), YoutubeVideo.class).sheet("youtube").doWrite(listMessage);
        }
    }
}
