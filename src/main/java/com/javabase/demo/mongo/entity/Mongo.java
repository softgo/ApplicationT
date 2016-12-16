package com.javabase.demo.mongo.entity;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 实体bean
 * 
 * @author bruce
 *
 */
@Document(collection="mongo")
public class Mongo implements Serializable {

	private static final long serialVersionUID = 1L;	@Id	private ObjectId _id;//   _id	private String title;//   title	private String description;//   description	private String by;//   by	private String url;//   url	private String tags;//   tags	private String likes;//   likes		public ObjectId get_id() {	    return this._id;	}	public void set_id(ObjectId _id) {	    this._id=_id;	}		public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}		public String getDescription() {	    return this.description;	}	public void setDescription(String description) {	    this.description=description;	}		public String getBy() {	    return this.by;	}	public void setBy(String by) {	    this.by=by;	}		public String getUrl() {	    return this.url;	}	public void setUrl(String url) {	    this.url=url;	}		public String getTags() {	    return this.tags;	}	public void setTags(String tags) {	    this.tags=tags;	}		public String getLikes() {	    return this.likes;	}	public void setLikes(String likes) {	    this.likes=likes;	}
	
}

