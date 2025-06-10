package com.project.demo.controller;

import com.project.demo.entity.TourismType;
import com.project.demo.service.TourismTypeService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *旅游类型：(TourismType)表控制层
 *
 */
@RestController
@RequestMapping("/tourism_type")
public class TourismTypeController extends BaseController<TourismType,TourismTypeService> {

    /**
     *旅游类型对象
     */
    @Autowired
    public TourismTypeController(TourismTypeService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
