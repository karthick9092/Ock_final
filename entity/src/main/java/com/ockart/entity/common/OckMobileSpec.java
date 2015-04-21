package com.ockart.entity.common;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ock_mobile_spec database table.
 * 
 */
@Entity
@Table(name="ock_mobile_spec")
public class OckMobileSpec implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="product_id")
	private long productId;

	@Column(name="2G_network")
	private String twoNetwork;

	@Column(name="3G_network")
	private String threeGnetwork;

	@Column(name="4G_network")
	private String foutGnetwork;

	@Column(name="alert_types")
	private String alertTypes;

	@Column(name="battery_type")
	private String batteryType;

	private String bluetooth;

	private String browser;

	@Lob
	@Column(name="cam_features")
	private String camFeatures;

	@Column(name="cam_video")
	private String camVideo;

	@Column(name="card_slot")
	private String cardSlot;

	private String chipset;

	private String cpu;

	private String dimensions;

	private String edge;

	private String gprs;

	private String gps;

	private String gpu;

	private String internal;

	@Column(name="loud_speaker")
	private String loudSpeaker;

	private String messaging;

	@Column(name="multi_touch")
	private String multiTouch;

	private String nfc;

	private String os;

	private String others;

	@Lob
	@Column(name="primary_cam")
	private String primaryCam;

	private String protection;

	private String radio;

	@Column(name="secondary_cam")
	private String secondaryCam;

	private String sensors;

	private String sim;

	private String size;

	@Lob
	private String speed;

	@Column(name="stand_by")
	private String standBy;

	@Column(name="talk_time")
	private String talkTime;

	private String type;

	private String usb;

	private String weight;

	private String wlan;

	public OckMobileSpec() {
	}

	public long getProductId() {
		return this.productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getTwoNetwork() {
		return twoNetwork;
	}

	public void setTwoNetwork(String twoNetwork) {
		this.twoNetwork = twoNetwork;
	}

	public String getThreeGnetwork() {
		return threeGnetwork;
	}

	public void setThreeGnetwork(String threeGnetwork) {
		this.threeGnetwork = threeGnetwork;
	}

	public String getFoutGnetwork() {
		return foutGnetwork;
	}

	public void setFoutGnetwork(String foutGnetwork) {
		this.foutGnetwork = foutGnetwork;
	}

	public String getAlertTypes() {
		return this.alertTypes;
	}

	public void setAlertTypes(String alertTypes) {
		this.alertTypes = alertTypes;
	}

	public String getBatteryType() {
		return this.batteryType;
	}

	public void setBatteryType(String batteryType) {
		this.batteryType = batteryType;
	}

	public String getBluetooth() {
		return this.bluetooth;
	}

	public void setBluetooth(String bluetooth) {
		this.bluetooth = bluetooth;
	}

	public String getBrowser() {
		return this.browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getCamFeatures() {
		return this.camFeatures;
	}

	public void setCamFeatures(String camFeatures) {
		this.camFeatures = camFeatures;
	}

	public String getCamVideo() {
		return this.camVideo;
	}

	public void setCamVideo(String camVideo) {
		this.camVideo = camVideo;
	}

	public String getCardSlot() {
		return this.cardSlot;
	}

	public void setCardSlot(String cardSlot) {
		this.cardSlot = cardSlot;
	}

	public String getChipset() {
		return this.chipset;
	}

	public void setChipset(String chipset) {
		this.chipset = chipset;
	}

	public String getCpu() {
		return this.cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getDimensions() {
		return this.dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public String getEdge() {
		return this.edge;
	}

	public void setEdge(String edge) {
		this.edge = edge;
	}

	public String getGprs() {
		return this.gprs;
	}

	public void setGprs(String gprs) {
		this.gprs = gprs;
	}

	public String getGps() {
		return this.gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}

	public String getGpu() {
		return this.gpu;
	}

	public void setGpu(String gpu) {
		this.gpu = gpu;
	}

	public String getInternal() {
		return this.internal;
	}

	public void setInternal(String internal) {
		this.internal = internal;
	}

	public String getLoudSpeaker() {
		return this.loudSpeaker;
	}

	public void setLoudSpeaker(String loudSpeaker) {
		this.loudSpeaker = loudSpeaker;
	}

	public String getMessaging() {
		return this.messaging;
	}

	public void setMessaging(String messaging) {
		this.messaging = messaging;
	}

	public String getMultiTouch() {
		return this.multiTouch;
	}

	public void setMultiTouch(String multiTouch) {
		this.multiTouch = multiTouch;
	}

	public String getNfc() {
		return this.nfc;
	}

	public void setNfc(String nfc) {
		this.nfc = nfc;
	}

	public String getOs() {
		return this.os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getOthers() {
		return this.others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getPrimaryCam() {
		return this.primaryCam;
	}

	public void setPrimaryCam(String primaryCam) {
		this.primaryCam = primaryCam;
	}

	public String getProtection() {
		return this.protection;
	}

	public void setProtection(String protection) {
		this.protection = protection;
	}

	public String getRadio() {
		return this.radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public String getSecondaryCam() {
		return this.secondaryCam;
	}

	public void setSecondaryCam(String secondaryCam) {
		this.secondaryCam = secondaryCam;
	}

	public String getSensors() {
		return this.sensors;
	}

	public void setSensors(String sensors) {
		this.sensors = sensors;
	}

	public String getSim() {
		return this.sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSpeed() {
		return this.speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getStandBy() {
		return this.standBy;
	}

	public void setStandBy(String standBy) {
		this.standBy = standBy;
	}

	public String getTalkTime() {
		return this.talkTime;
	}

	public void setTalkTime(String talkTime) {
		this.talkTime = talkTime;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsb() {
		return this.usb;
	}

	public void setUsb(String usb) {
		this.usb = usb;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWlan() {
		return this.wlan;
	}

	public void setWlan(String wlan) {
		this.wlan = wlan;
	}

}