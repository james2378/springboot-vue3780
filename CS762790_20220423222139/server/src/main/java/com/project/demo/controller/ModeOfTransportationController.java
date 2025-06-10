package com.project.demo.controller;

import com.project.demo.entity.ModeOfTransportation;
import com.project.demo.service.ModeOfTransportationService;
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
 *交通方式：(ModeOfTransportation)表控制层
 *
 */
@RestController
@RequestMapping("/mode_of_transportation")
public class ModeOfTransportationController extends BaseController<ModeOfTransportation,ModeOfTransportationService> {

    /**
     *交通方式对象
     */
    @Autowired
    public ModeOfTransportationController(ModeOfTransportationService service) {
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
