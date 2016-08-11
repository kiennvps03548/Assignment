package quanly.taikhoan.nv;

import android.provider.ContactsContract.CommonDataKinds.Email;

public class NhanVien {
	String id;
	String name;
	String Gender;
	String Date;
	Number Phone;
	String Email;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public Number getPhone() {
		return Phone;
	}

	public void setPhone(Number phone) {
		Phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassWord() {
		return PassWord;
	}

	public void setPassWord(String passWord) {
		PassWord = passWord;
	}

	String Username;
	String PassWord;
	
	public NhanVien() {
		super();
	}

	public NhanVien(String id, String name, String gender, String date,
			Number phone, String email, String username, String passWord) {
		super();
		this.id = id;
		this.name = name;
		Gender = gender;
		Date = date;
		Phone = phone;
		Email = email;
		Username = username;
		PassWord = passWord;
	}
	
	
}
