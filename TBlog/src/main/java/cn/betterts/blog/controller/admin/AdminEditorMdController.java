package cn.betterts.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@Controller
@RequestMapping("/upload")
public class AdminEditorMdController {

    public final String rootPath = "http:"+File.separator+File.separator+"www.betterts.cn"+File.separator+"upload"+File.separator;

    //    给editormarkdown插件的
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> upload(HttpServletRequest request) throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        Map<String ,Object> result=new HashMap<String ,Object>();

        //创建年月文件夹
        Calendar date = Calendar.getInstance();
        File dateDirs = new File(date.get(Calendar.YEAR)  + File.separator + (date.get(Calendar.MONTH) + 1));
        String dateDirsPath = dateDirs.getPath();




        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 取得上传文件
                MultipartFile f = multiRequest.getFile(iter.next());
                if (f != null) {
                    // 取得当前上传文件的文件名称
                    String fileName = f.getOriginalFilename();
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (fileName.trim() != "") {
                        // 定义上传路径

                        String uploadpath = request.getSession().getServletContext().getRealPath("upload");


                        File targetFile = new File(uploadpath+File.separator+dateDirs  , fileName);

                        if (!targetFile.exists()) {
                            targetFile.mkdirs();
                        }

                        //保存
                        f.transferTo(targetFile);
                        result.put("success",1);
                        result.put("message","上传成功");
                        String  path = rootPath + dateDirsPath + File.separator + fileName;
                        result.put("url", path);
                    }
                }
            }
        }else{
            result.put("success",0);
            result.put("message","上传失败");
        }
        System.out.println(result.toString());
        return result;
    }
}
