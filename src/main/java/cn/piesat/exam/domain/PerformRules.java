package cn.piesat.exam.domain;

import javax.persistence.*;

@Table(name = "`perform_rules`")
public class PerformRules {
    @Id
    @Column(name = "`id`")
    private Integer id;

    @Column(name = "`type`")
    private String type;

    @Column(name = "`kpi`")
    private String kpi;

    @Column(name = "`weight`")
    private Integer weight;

    @Column(name = "`rules`")
    private String rules;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * @return kpi
     */
    public String getKpi() {
        return kpi;
    }

    /**
     * @param kpi
     */
    public void setKpi(String kpi) {
        this.kpi = kpi == null ? null : kpi.trim();
    }

    /**
     * @return weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * @param weight
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * @return rules
     */
    public String getRules() {
        return rules;
    }

    /**
     * @param rules
     */
    public void setRules(String rules) {
        this.rules = rules == null ? null : rules.trim();
    }
}