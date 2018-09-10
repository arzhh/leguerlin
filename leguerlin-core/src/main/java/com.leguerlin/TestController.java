package com.leguerlin;

import com.leguerlin.model.Theme;
import com.leguerlin.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private static final String template = "Theme: %s, Picture: %s";
    @Autowired
    private ThemeRepository themeRepository;

    @RequestMapping("/test")
    public String greeting(@RequestParam(value="value") String value) {
        if (StringUtils.isEmpty(value)){
            return "No value passed";
        }
        try {
            Theme theme = themeRepository.findById(Long.parseLong(value)).orElse(null);
            if (theme == null) {
                return "No theme found";
            } else {

                return String.format(template, theme.getName(), theme.getPicture().getName());
            }
        } catch (Exception e){
            return "Wrong value passed";
        }
    }
}