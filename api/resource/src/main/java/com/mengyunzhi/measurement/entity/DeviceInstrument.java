package com.mengyunzhi.measurement.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.DeviceInstrumentJsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by 安强 on 2017/5/31.
 * 装置授权检定项目与精度，测量范围关联
 */
@Entity
@Immutable
@ApiModel(value = "DeviceInstrument (装置授权检定项目)", description = "装置授权检定项目实体")
public class DeviceInstrument implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @ApiModelProperty("器具二级类别")
    @JsonView({NoneJsonView.class, DeviceInstrumentJsonView.ToDeviceSet.class})
    private InstrumentType instrumentType;

    @ManyToOne
    @ApiModelProperty("计量标准装置")
    @JsonView({NoneJsonView.class, DeviceInstrumentJsonView.ToDeviceSet.class})
    private DeviceSet deviceSet;

    @ManyToOne
    @ApiModelProperty("对应的最小测量范围")        //关联测量范围
    @JsonView({NoneJsonView.class, DeviceInstrumentJsonView.ToDeviceSet.class})
    private MeasureScale minMeasureScale;

    @ManyToOne
    @ApiModelProperty("对应的最大测量范围")        //关联测量范围
    @JsonView({NoneJsonView.class, DeviceInstrumentJsonView.ToDeviceSet.class})
    private MeasureScale maxMeasureScale;

    @ManyToOne
    @ApiModelProperty("对应的精度")         //关联精度
    @JsonView({NoneJsonView.class, DeviceInstrumentJsonView.ToDeviceSet.class})
    private Accuracy accuracy;

    public DeviceInstrument() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }

    public DeviceSet getDeviceSet() {
        return deviceSet;
    }

    public void setDeviceSet(DeviceSet deviceSet) {
        this.deviceSet = deviceSet;
    }

    public MeasureScale getMinMeasureScale() {
        return minMeasureScale;
    }

    public void setMinMeasureScale(MeasureScale minMeasureScale) {
        this.minMeasureScale = minMeasureScale;
    }

    public Accuracy getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Accuracy accuracy) {
        this.accuracy = accuracy;
    }

    public MeasureScale getMaxMeasureScale() {
        return maxMeasureScale;
    }

    public void setMaxMeasureScale(MeasureScale maxMeasureScale) {
        this.maxMeasureScale = maxMeasureScale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceInstrument that = (DeviceInstrument) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
