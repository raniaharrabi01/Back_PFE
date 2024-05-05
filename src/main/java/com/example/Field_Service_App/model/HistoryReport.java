package com.example.Field_Service_App.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Report_History")
public class HistoryReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "template_id")
    private Template template;
    private String type_data_source;
    private Date genaration_date;
    private String export_format;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Date getGenaration_date() {
        return genaration_date;
    }

    public String getExport_format() {
        return export_format;
    }

    public String getType_data_source() {
        return type_data_source;
    }

    public Template getTemplate() {
        return template;
    }

    public void setExport_format(String export_format) {
        this.export_format = export_format;
    }

    public void setGenaration_date(Date genaration_date) {
        this.genaration_date = genaration_date;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public void setType_data_source(String type_data_source) {
        this.type_data_source = type_data_source;
    }
}
