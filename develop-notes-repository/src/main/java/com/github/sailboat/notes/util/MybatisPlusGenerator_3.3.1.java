//package com.github.sailboat.notes.util;
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.annotation.FieldFill;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.GlobalConfig;
//import com.baomidou.mybatisplus.generator.config.PackageConfig;
//import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//import com.baomidou.mybatisplus.generator.config.po.TableFill;
//import com.baomidou.mybatisplus.generator.config.rules.DateType;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//
//import java.util.ArrayList;
//
//// 代码自动生成器
//public class com.github.sailboat.notes.mybatisplusgen.MybatisPlusGenerator {
//    public static void main(String[] args) {
//        // 需要构建一个 代码自动生成器 对象
//        AutoGenerator mpg = new AutoGenerator();
//        // 配置策略
//
//        // 1、全局配置
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/src/main/java");
//        gc.setAuthor("狂神说");
//        gc.setOpen(false);
//        gc.setFileOverride(false); // 是否覆盖
//        gc.setServiceName("%sService"); // 去Service的I前缀
//        gc.setServiceImplName("%sServiceDao");
//        gc.setIdType(IdType.ID_WORKER);
//        gc.setDateType(DateType.ONLY_DATE);
//        gc.setSwagger2(true);
//        mpg.setGlobalConfig(gc);
//
//        //2、设置数据源
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://192.168.3.169:3306/asset_warehouse?" +
//                "useSSL=false&useUnicode=true&characterEncoding=utf-8&" +
//                "serverTimezone=GMT%2B8");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("asset_warehouse");
//        dsc.setPassword("asset_warehouse");
//        dsc.setDbType(DbType.MYSQL);
//        mpg.setDataSource(dsc);
//
//        //3、包的配置
//        PackageConfig pc = new PackageConfig();
//        pc.setModuleName("develops-notes");
//        pc.setParent("com.github.sailboat.notes");
//        pc.setEntity("entity");
//        pc.setMapper("mapper");
//        pc.setService("service");
//        pc.setController("");
//        mpg.setPackageInfo(pc);
//
//        //4、策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setInclude("wms_stock", "wms_warehouse"); // 设置要映射的表名
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setEntityLombokModel(true); // 自动lombok；
//        strategy.setLogicDeleteFieldName("deleted");
//
//        // 自动填充配置
//        ArrayList<TableFill> tableFills = new ArrayList<>();
//        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
//        tableFills.add(gmtCreate);
//        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
//        tableFills.add(gmtModified);
//        strategy.setTableFillList(tableFills);
//        // 乐观锁
//        strategy.setVersionFieldName("version");
//        strategy.setRestControllerStyle(true);
//        strategy.setControllerMappingHyphenStyle(true); //localhost:8080/hello_id_2
//        mpg.setStrategy(strategy);
//        mpg.execute(); //执行
//    }
//}
//
