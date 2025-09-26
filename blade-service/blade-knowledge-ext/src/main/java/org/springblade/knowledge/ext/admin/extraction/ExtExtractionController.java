package org.springblade.knowledge.ext.admin.extraction;

import lombok.extern.slf4j.Slf4j;
import org.springblade.knowledge.ext.service.deepke.DeepkeExtractionService;
import org.springblade.knowledge.ext.service.neo4j.service.ExtNeo4jService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;


/**
 * 知识抽取
 */
@Slf4j
@RestController
@RequestMapping("/extExtraction")
public class ExtExtractionController {
    @Resource
    private DeepkeExtractionService kmcExtractionService;
    @Resource
    private ExtNeo4jService kmcNeo4jService;

//    /**
//     * 三元组抽取
//     *
//     * @return
//     */
//    @PostMapping("/extraction")
//    public AjaxResult extraction(@RequestBody ExtExtractionDTO extractionDTO) {
//        return kmcExtractionService.extraction(extractionDTO);
//    }


//    @GetMapping("/getByName")
//    public AjaxResult getByName(String name) {
//        return kmcNeo4jService.getByName(name);
//    }
}
