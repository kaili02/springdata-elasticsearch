package com.kl.es;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Document(indexName = "shopping", shards = 3, replicas = 1)
public class Product {
    /**
     * 主键是String save后可以回写id的值，number类型是 save后无法回写，但是不会报错
     */
    //必须有 id,这里的 id 是全局唯一的标识，等同于 es 中的"_id"
    @Id
    private String id;//商品唯一标识


    /**
     *type : 字段数据类型
     *analyzer : 分词器类型
     *index : 是否索引(默认:true)
     *Keyword : 短语,不进行分词
     */

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;//商品名称
    @Field(type = FieldType.Keyword)
    private String category;//分类名称
    @Field(type = FieldType.Double)
    private Double price;//商品价格
    @Field(type = FieldType.Keyword, index = false)
    private String images;//图片地址

    @CreatedDate // 无效
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private Date createDt;

    @LastModifiedDate// 无效
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute)
    private Date updateDt;
}