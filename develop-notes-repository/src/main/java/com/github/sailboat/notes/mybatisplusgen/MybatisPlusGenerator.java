package com.github.sailboat.notes.mybatisplusgen;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MybatisPlusGenerator {

    private static final String MAPPER_EXT = "src/main/resources/mapper/ext";
    private static final String MAPPER = "src/main/resources/mapper";

    public MybatisPlusGenerator() {
    }

    private static void setDataSource(AutoGenerator gen, Properties properties) {
        gen.setDataSource((new DataSourceConfig()).setDbType(DbType.MYSQL).setDriverName(properties.getProperty("datasource.driver")).setUrl(properties.getProperty("datasource.url")).setUsername(properties.getProperty("datasource.username")).setPassword(properties.getProperty("datasource.password")));
    }

    private static void setGlobalConfig(AutoGenerator gen, Properties properties, String baseProjectPath) {
        gen.setGlobalConfig((new GlobalConfig()).setOutputDir(baseProjectPath + "/src/main/java").setFileOverride(false).setActiveRecord(false).setEnableCache(false).setBaseResultMap(true).setBaseColumnList(true).setOpen(false).setAuthor(properties.getProperty("gconfig.author")).setIdType(IdType.ASSIGN_ID).setDateType(DateType.ONLY_DATE).setEntityName("%sEntity").setMapperName("%sMapper").setXmlName("%sMapper").setServiceName("%sDao").setServiceImplName("%sDaoImpl"));
    }

    private static void setPackageInfo(AutoGenerator gen, Properties properties) {
        gen.setPackageInfo((new PackageConfig()).setParent(properties.getProperty("project.package")).setEntity("po").setMapper("mapper").setService("dao").setServiceImpl("dao.impl").setXml("mapper.sys"));
    }

    private static void setStrategy(AutoGenerator gen, Properties properties) {
        List<TableFill> tableFills = new ArrayList();
        TableFill updateTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateName = new TableFill("update_name", FieldFill.INSERT_UPDATE);
        tableFills.add(updateTime);
        tableFills.add(updateName);
        gen.setStrategy((new StrategyConfig()).setNaming(NamingStrategy.underline_to_camel).setInclude(properties.getProperty("gen.tables").split(",")).setSuperEntityClass(properties.getProperty("gen.superEntity")).setSuperEntityColumns(new String[]{"created_by", "created_at", "updated_at", "updated_by", "deleted", "object_id"}).setTableFillList(tableFills).setSuperMapperClass(properties.getProperty("gen.superMapper", "com.baomidou.mybatisplus.core.mapper.BaseMapper")).setSuperServiceImplClass(properties.getProperty("gen.superDao", "com.baomidou.mybatisplus.extension.service.impl.ServiceImpl")).setEntityLombokModel(true));
    }

    private static void setCustomCfg(AutoGenerator gen, final Properties properties, final String baseProjectPath) {
        InjectionConfig cfg = new InjectionConfig() {
            public void initMap() {
                Map<String, Object> map = new HashMap();
                map.put("MapperExt", "src/main/resources/mapper/ext".replace('/', '.'));
                this.setMap(map);
            }
        };
        List<FileOutConfig> fileOutList = new ArrayList();
        fileOutList.add(new FileOutConfig("templates/mapper.xml.vm") {
            public String outputFile(TableInfo tableInfo) {
                return baseProjectPath + "/" + properties.getProperty("gen.mapperPath", "src/main/resources/mapper") + "/" + tableInfo.getMapperName() + ".xml";
            }
        });
        fileOutList.add(new FileOutConfig("templates/mapperExt.xml.vm") {
            public String outputFile(TableInfo tableInfo) {
                return baseProjectPath + "/" + properties.getProperty("gen.mapperExtPath", "src/main/resources/mapper/ext") + "/" + tableInfo.getMapperName() + "Ext.xml";
            }
        });
        fileOutList.add(new FileOutConfig("templates/mapperExt.java.vm") {
            public String outputFile(TableInfo tableInfo) {
                return baseProjectPath + "/src/main/java/" + properties.getProperty("project.package").replace('.', '/') + "/mapper/ext/" + tableInfo.getMapperName() + "Ext.java";
            }
        });
        cfg.setFileOutConfigList(fileOutList);
        cfg.setFileCreate((configBuilder, fileType, filePath) -> {
            if ((filePath.contains("MapperExt") || fileType == FileType.SERVICE || fileType == FileType.SERVICE_IMPL) && (new File(filePath)).exists()) {
                return false;
            } else {
                File file = new File(filePath);
                boolean exist = file.exists();
                if (!exist) {
                    file.getParentFile().mkdirs();
                }

                return true;
            }
        });
        gen.setCfg(cfg);
    }

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        Resource resource = new ClassPathResource("generator.properties");
        properties.load(resource.getInputStream());
        String baseProjectPath = System.getProperty("user.dir");
        AutoGenerator gen = new AutoGenerator();
        setDataSource(gen, properties);
        setGlobalConfig(gen, properties, baseProjectPath);
        setStrategy(gen, properties);
        setPackageInfo(gen, properties);
        setCustomCfg(gen, properties, baseProjectPath);
        gen.setTemplate((new TemplateConfig()).setXml(null).setController("").setServiceImpl("templates/myServiceImpl.java"));
        gen.execute();

    }

}
