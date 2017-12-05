package wx.common.entity;

/**
 * 获取我的简单信息包括设置时使用
 * 
 * @author syf
 * 
 */
public class UsimpleSettingPojo {

	private String uid;
	private String nickname;// 昵称
	private String phone;// 电话
	private Integer sound;// 声音通知
	private String autograph;// 签名

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSound() {
		return sound;
	}

	public void setSound(Integer sound) {
		this.sound = sound;
	}

	public String getAutograph() {
		return autograph;
	}

	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}
}
