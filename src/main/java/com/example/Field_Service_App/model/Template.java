package com.example.Field_Service_App.model;


import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Template")
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "template_name")
    private String name;
    private Date save_date;
    private Date modify_date;
    @Column(columnDefinition = "LONGTEXT")
    private String html_data;
    @Column(columnDefinition = "LONGTEXT")
    private String css_data;
    private List<String> context;


    public Template(){
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getModelName() {
        return name;
    }
    public void setModelName(String name) {
        this.name = name;
    }
    public void setSaveDate(Date date) {
        this.save_date = date;
    }
    public Date getSaveDate() {return save_date;}
    public Date getModifyDate() {return modify_date;}
    public void setModifyDate(Date date) {this.modify_date = date;}
    public String getHtmlData() {
        return html_data;
    }
    public void setHtmlData(String HtmlData) {this.html_data = HtmlData;}
    public String getCssData(){return css_data;}
    public void setCssData(String CssData){this.css_data = CssData;}
    public void setContext(List<String> context) {this.context = context;}
    public List<String> getContext() {return context;}

}
