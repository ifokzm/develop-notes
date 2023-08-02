package com.github.sailboat.notes.util;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

public class CodeGenerator {

    public static void main(String[] args) {
        FastAutoGenerator.create(new DataSourceConfig.Builder("jdbc:mysql://192.168.3.169:3306/asset_warehouse?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false&rewriteBatchedStatements=true", "asset_warehouse", "asset_warehouse"))
                .globalConfig(builder -> builder.author("雨泽明")
                        .commentDate("yyyy-MM-dd")
                        .outputDir(System.getProperty("user.dir") + "/src/main/java")
                        .disableOpenDir())

                .packageConfig(builder ->
                        builder.parent("com.github.sailboat.notes")
                                .moduleName("")
                                .entity("entity")
                                .mapper("mapper")
                                .service("Service")
                                .serviceImpl("ServiceImpl")
                                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "/src/main/resources/mapper")))
//                .injectionConfig(builder -> {
//
//                })
                .strategyConfig(builder ->
                        builder.addInclude("wms_stock_allot_match").addTablePrefix("t_")
                                .entityBuilder()
                                .columnNaming(NamingStrategy.underline_to_camel)
                                .logicDeletePropertyName("deleted")
                                .addTableFills(
                                        new Column("create_time", FieldFill.INSERT),
                                        new Column("update_time", FieldFill.INSERT_UPDATE)
                                )
                                .enableLombok()
                                .enableTableFieldAnnotation()
                                .enableActiveRecord()
                                .enableColumnConstant()

                                // mapper 策略配置
                                .mapperBuilder()
                                .superClass(BaseMapper.class)
                                .formatMapperFileName("%sMapper")
                                .formatXmlFileName("%sXml")
                                .enableBaseResultMap()
                                .enableBaseColumnList()
                                .enableMapperAnnotation()

                                // serviceDao 策略配置
                                .serviceBuilder()
                                .formatServiceFileName("%sService")
                                .formatServiceImplFileName("%sServiceImpl")

                                // controller 策略配置
                                .controllerBuilder()
                                .superClass(RestController.class)
                                .enableRestStyle()
                )
//                .templateConfig(builder ->
//                    builder.mapperXml("templates/mapperExt.xml.vm")
//                )
                .execute();

    }

}
