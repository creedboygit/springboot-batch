package com.valletta.pass.controller.admin;

import com.valletta.pass.service.packaze.PackageService;
import com.valletta.pass.service.pass.BulkPassService;
import com.valletta.pass.service.statistics.StatisticsService;
import com.valletta.pass.service.user.UserGroupMappingService;
import com.valletta.pass.util.LocalDateTimeUtils;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminViewController {

    private final BulkPassService bulkPassService;
    private final PackageService packageService;
    private final UserGroupMappingService userGroupMappingService;
    private final StatisticsService statisticsService;

    @GetMapping
    public ModelAndView home(ModelAndView modelAndView, @RequestParam("to") String toString) {
        LocalDateTime to = LocalDateTimeUtils.parseDate(toString);

        modelAndView.addObject("chartData", statisticsService.makeChartData(to));
        modelAndView.setViewName("admin/index");
        return modelAndView;
    }

    @GetMapping("/bulk-pass")
    public ModelAndView registerBulkPass(ModelAndView modelAndView) {
        modelAndView.addObject("bulkPasses", bulkPassService.getAllBulkPasses());
        modelAndView.addObject("packages", packageService.getAllPackage());
        modelAndView.addObject("userGroupIds", userGroupMappingService.getAllUserGroupIds());
        modelAndView.addObject("request", new BulkPassRequest());
        modelAndView.setViewName("admin/bulk-pass");

        return modelAndView;
    }

    @PostMapping("/bulk-pass")
    public String addBulkPass(@ModelAttribute("request") BulkPassRequest request, Model model) {
        bulkPassService.addBulkPass(request);
        return "redirect:/admin/bulk-pass";
    }
}
