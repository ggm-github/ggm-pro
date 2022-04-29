package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;
import java.util.Date;

public class TeamImage implements Serializable {

	private Integer id;
	/** 关联team_video表中的id */
	private Integer teamvideoId;
	/** 缩略图url */
	private String thumbnailUrl;
	/** 动态图片url */
	private String imageUrl;
	/** 图片顺序 */
	private Integer imageIndex;
	/** 入库时间 */
	private Date inserttime;
	/** 图片宽高 */
	private int imageWidth;
	private int imageHeight;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTeamvideoId() {
		return teamvideoId;
	}

	public void setTeamvideoId(Integer teamvideoId) {
		this.teamvideoId = teamvideoId;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getImageIndex() {
		return imageIndex;
	}

	public void setImageIndex(Integer imageIndex) {
		this.imageIndex = imageIndex;
	}

	public Date getInserttime() {
		return inserttime;
	}

	public void setInserttime(Date inserttime) {
		this.inserttime = inserttime;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}
}
