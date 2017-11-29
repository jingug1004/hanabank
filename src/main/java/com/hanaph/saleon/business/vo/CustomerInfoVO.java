/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.vo;

/**
 * <pre>
 * Class Name : CustomerInfoVO.java
 * 설명 : 고객등록 VO
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 17.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 12. 17.
 */
public class CustomerInfoVO {
	
	private String cust_id; // 거래처 코드
	private String cust_nm; // 거래처 명
	private String cust_id2; // 거래처 코드2(팝업버튼 생성을 위한 변수)
	private String sawon_id; // 담당사원코드
	private String sawon_nm; // 담당사원명
	
	private String customer_id; // 고객 코드
	private String customer_nm; // 고객명
	private String sex; // 성별
	private String birth_day; // 생일
	private String act_birth_day; // 실제생일
	private String marry_yn; // 결혼여부
	private String marry_day; // 결혼기념일
	private String child_kind; // 자녀관계
	private String disposition; // 성향
	private String religion; // 종교
	private String highschool; // 고등학교
	private String university; // 대학교
	private String zip; // 우편번호
	private String address1; // 주소
	private String address2; // 상세주소
	private String tel; // 전화번호
	private String mobile; // 핸드폰번호
	private String fax; // fax 번호
	private String email; // email
	private String car_no; // 차량번호
	private String car_color; // 차종색상
	private String foreign_study_yn; // 해외연수여부
	private String rank; // 직위
	private String lesson; // 전문과
	private String hospital; // 수련병원
	private String major; // 전공
	private String main_buying; // 주거래 도매
	private String human_rel; // 대인관계
	private String hobby; // 취미
	private String taboo_list; // 금기사항
	private String gita; // 기타사항
	
	private String cust_nm1; // 거래처명
	private String vou_no; // 사업자 번호
	private String president; // 대표자명
	private String bupin_no; // 법인번호
	private String uptae; // 업태
	private String jongmok; // 종목
	private String addr1; // 주소
	private String addr2; // 상세주소
	private String hp; // 핸드폰번호
	private String cust_gb1; // 거래처 구분
	private String room_cnt; // 병실 수
	private String open_date; // 개업일
	private String start_ymd; // 거래개시일
	private String use_ymd; // 중지일자
	private String submit_date; //결제일 
	private String bedno; // BED 수
	private String seq; // 일련번호
	
	private String hakheo_nm; // 학회명
	private String datef; // 기간 fr
	private String datet; // 기간 to
	private String jiwi; // 직위
	private String gwansimdo; // 관심도
	private String relation; // 관계
	private String name; // 이름
	private String birthday; // 생일
	private String job; // 직업
	private String sdate; // 날짜
	private String kind; // 종류
	private String bigo; // 비고
	private String friendship; // 친구관계
	private String corp_nm; // 병원/기관/제약회사명
	
	private String tableType; // 조회할 테이블 명
	
	private int total_cnt; // 총 목록 수

	/**
	 * @return the cust_id
	 */
	public String getCust_id() {
		return cust_id;
	}

	/**
	 * @param cust_id the cust_id to set
	 */
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	/**
	 * @return the cust_nm
	 */
	public String getCust_nm() {
		return cust_nm;
	}

	/**
	 * @param cust_nm the cust_nm to set
	 */
	public void setCust_nm(String cust_nm) {
		this.cust_nm = cust_nm;
	}

	/**
	 * @return the cust_id2
	 */
	public String getCust_id2() {
		return cust_id2;
	}

	/**
	 * @param cust_id2 the cust_id2 to set
	 */
	public void setCust_id2(String cust_id2) {
		this.cust_id2 = cust_id2;
	}

	/**
	 * @return the sawon_id
	 */
	public String getSawon_id() {
		return sawon_id;
	}

	/**
	 * @param sawon_id the sawon_id to set
	 */
	public void setSawon_id(String sawon_id) {
		this.sawon_id = sawon_id;
	}

	/**
	 * @return the sawon_nm
	 */
	public String getSawon_nm() {
		return sawon_nm;
	}

	/**
	 * @param sawon_nm the sawon_nm to set
	 */
	public void setSawon_nm(String sawon_nm) {
		this.sawon_nm = sawon_nm;
	}

	/**
	 * @return the customer_id
	 */
	public String getCustomer_id() {
		return customer_id;
	}

	/**
	 * @param customer_id the customer_id to set
	 */
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	/**
	 * @return the customer_nm
	 */
	public String getCustomer_nm() {
		return customer_nm;
	}

	/**
	 * @param customer_nm the customer_nm to set
	 */
	public void setCustomer_nm(String customer_nm) {
		this.customer_nm = customer_nm;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the birth_day
	 */
	public String getBirth_day() {
		return birth_day;
	}

	/**
	 * @param birth_day the birth_day to set
	 */
	public void setBirth_day(String birth_day) {
		this.birth_day = birth_day;
	}

	/**
	 * @return the act_birth_day
	 */
	public String getAct_birth_day() {
		return act_birth_day;
	}

	/**
	 * @param act_birth_day the act_birth_day to set
	 */
	public void setAct_birth_day(String act_birth_day) {
		this.act_birth_day = act_birth_day;
	}

	/**
	 * @return the marry_yn
	 */
	public String getMarry_yn() {
		return marry_yn;
	}

	/**
	 * @param marry_yn the marry_yn to set
	 */
	public void setMarry_yn(String marry_yn) {
		this.marry_yn = marry_yn;
	}

	/**
	 * @return the marry_day
	 */
	public String getMarry_day() {
		return marry_day;
	}

	/**
	 * @param marry_day the marry_day to set
	 */
	public void setMarry_day(String marry_day) {
		this.marry_day = marry_day;
	}

	/**
	 * @return the child_kind
	 */
	public String getChild_kind() {
		return child_kind;
	}

	/**
	 * @param child_kind the child_kind to set
	 */
	public void setChild_kind(String child_kind) {
		this.child_kind = child_kind;
	}

	/**
	 * @return the disposition
	 */
	public String getDisposition() {
		return disposition;
	}

	/**
	 * @param disposition the disposition to set
	 */
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	/**
	 * @return the religion
	 */
	public String getReligion() {
		return religion;
	}

	/**
	 * @param religion the religion to set
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}

	/**
	 * @return the highschool
	 */
	public String getHighschool() {
		return highschool;
	}

	/**
	 * @param highschool the highschool to set
	 */
	public void setHighschool(String highschool) {
		this.highschool = highschool;
	}

	/**
	 * @return the university
	 */
	public String getUniversity() {
		return university;
	}

	/**
	 * @param university the university to set
	 */
	public void setUniversity(String university) {
		this.university = university;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the car_no
	 */
	public String getCar_no() {
		return car_no;
	}

	/**
	 * @param car_no the car_no to set
	 */
	public void setCar_no(String car_no) {
		this.car_no = car_no;
	}

	/**
	 * @return the car_color
	 */
	public String getCar_color() {
		return car_color;
	}

	/**
	 * @param car_color the car_color to set
	 */
	public void setCar_color(String car_color) {
		this.car_color = car_color;
	}

	/**
	 * @return the foreign_study_yn
	 */
	public String getForeign_study_yn() {
		return foreign_study_yn;
	}

	/**
	 * @param foreign_study_yn the foreign_study_yn to set
	 */
	public void setForeign_study_yn(String foreign_study_yn) {
		this.foreign_study_yn = foreign_study_yn;
	}

	/**
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 * @return the lesson
	 */
	public String getLesson() {
		return lesson;
	}

	/**
	 * @param lesson the lesson to set
	 */
	public void setLesson(String lesson) {
		this.lesson = lesson;
	}

	/**
	 * @return the hospital
	 */
	public String getHospital() {
		return hospital;
	}

	/**
	 * @param hospital the hospital to set
	 */
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	/**
	 * @return the major
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * @param major the major to set
	 */
	public void setMajor(String major) {
		this.major = major;
	}

	/**
	 * @return the main_buying
	 */
	public String getMain_buying() {
		return main_buying;
	}

	/**
	 * @param main_buying the main_buying to set
	 */
	public void setMain_buying(String main_buying) {
		this.main_buying = main_buying;
	}

	/**
	 * @return the human_rel
	 */
	public String getHuman_rel() {
		return human_rel;
	}

	/**
	 * @param human_rel the human_rel to set
	 */
	public void setHuman_rel(String human_rel) {
		this.human_rel = human_rel;
	}

	/**
	 * @return the hobby
	 */
	public String getHobby() {
		return hobby;
	}

	/**
	 * @param hobby the hobby to set
	 */
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	/**
	 * @return the taboo_list
	 */
	public String getTaboo_list() {
		return taboo_list;
	}

	/**
	 * @param taboo_list the taboo_list to set
	 */
	public void setTaboo_list(String taboo_list) {
		this.taboo_list = taboo_list;
	}

	/**
	 * @return the gita
	 */
	public String getGita() {
		return gita;
	}

	/**
	 * @param gita the gita to set
	 */
	public void setGita(String gita) {
		this.gita = gita;
	}

	/**
	 * @return the cust_nm1
	 */
	public String getCust_nm1() {
		return cust_nm1;
	}

	/**
	 * @param cust_nm1 the cust_nm1 to set
	 */
	public void setCust_nm1(String cust_nm1) {
		this.cust_nm1 = cust_nm1;
	}

	/**
	 * @return the vou_no
	 */
	public String getVou_no() {
		return vou_no;
	}

	/**
	 * @param vou_no the vou_no to set
	 */
	public void setVou_no(String vou_no) {
		this.vou_no = vou_no;
	}

	/**
	 * @return the president
	 */
	public String getPresident() {
		return president;
	}

	/**
	 * @param president the president to set
	 */
	public void setPresident(String president) {
		this.president = president;
	}

	/**
	 * @return the bupin_no
	 */
	public String getBupin_no() {
		return bupin_no;
	}

	/**
	 * @param bupin_no the bupin_no to set
	 */
	public void setBupin_no(String bupin_no) {
		this.bupin_no = bupin_no;
	}

	/**
	 * @return the uptae
	 */
	public String getUptae() {
		return uptae;
	}

	/**
	 * @param uptae the uptae to set
	 */
	public void setUptae(String uptae) {
		this.uptae = uptae;
	}

	/**
	 * @return the jongmok
	 */
	public String getJongmok() {
		return jongmok;
	}

	/**
	 * @param jongmok the jongmok to set
	 */
	public void setJongmok(String jongmok) {
		this.jongmok = jongmok;
	}

	/**
	 * @return the addr1
	 */
	public String getAddr1() {
		return addr1;
	}

	/**
	 * @param addr1 the addr1 to set
	 */
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	/**
	 * @return the addr2
	 */
	public String getAddr2() {
		return addr2;
	}

	/**
	 * @param addr2 the addr2 to set
	 */
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	/**
	 * @return the hp
	 */
	public String getHp() {
		return hp;
	}

	/**
	 * @param hp the hp to set
	 */
	public void setHp(String hp) {
		this.hp = hp;
	}

	/**
	 * @return the cust_gb1
	 */
	public String getCust_gb1() {
		return cust_gb1;
	}

	/**
	 * @param cust_gb1 the cust_gb1 to set
	 */
	public void setCust_gb1(String cust_gb1) {
		this.cust_gb1 = cust_gb1;
	}

	/**
	 * @return the room_cnt
	 */
	public String getRoom_cnt() {
		return room_cnt;
	}

	/**
	 * @param room_cnt the room_cnt to set
	 */
	public void setRoom_cnt(String room_cnt) {
		this.room_cnt = room_cnt;
	}

	/**
	 * @return the open_date
	 */
	public String getOpen_date() {
		return open_date;
	}

	/**
	 * @param open_date the open_date to set
	 */
	public void setOpen_date(String open_date) {
		this.open_date = open_date;
	}

	/**
	 * @return the start_ymd
	 */
	public String getStart_ymd() {
		return start_ymd;
	}

	/**
	 * @param start_ymd the start_ymd to set
	 */
	public void setStart_ymd(String start_ymd) {
		this.start_ymd = start_ymd;
	}

	/**
	 * @return the use_ymd
	 */
	public String getUse_ymd() {
		return use_ymd;
	}

	/**
	 * @param use_ymd the use_ymd to set
	 */
	public void setUse_ymd(String use_ymd) {
		this.use_ymd = use_ymd;
	}

	/**
	 * @return the submit_date
	 */
	public String getSubmit_date() {
		return submit_date;
	}

	/**
	 * @param submit_date the submit_date to set
	 */
	public void setSubmit_date(String submit_date) {
		this.submit_date = submit_date;
	}

	/**
	 * @return the bedno
	 */
	public String getBedno() {
		return bedno;
	}

	/**
	 * @param bedno the bedno to set
	 */
	public void setBedno(String bedno) {
		this.bedno = bedno;
	}

	/**
	 * @return the seq
	 */
	public String getSeq() {
		return seq;
	}

	/**
	 * @param seq the seq to set
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}

	/**
	 * @return the hakheo_nm
	 */
	public String getHakheo_nm() {
		return hakheo_nm;
	}

	/**
	 * @param hakheo_nm the hakheo_nm to set
	 */
	public void setHakheo_nm(String hakheo_nm) {
		this.hakheo_nm = hakheo_nm;
	}

	/**
	 * @return the datef
	 */
	public String getDatef() {
		return datef;
	}

	/**
	 * @param datef the datef to set
	 */
	public void setDatef(String datef) {
		this.datef = datef;
	}

	/**
	 * @return the datet
	 */
	public String getDatet() {
		return datet;
	}

	/**
	 * @param datet the datet to set
	 */
	public void setDatet(String datet) {
		this.datet = datet;
	}

	/**
	 * @return the jiwi
	 */
	public String getJiwi() {
		return jiwi;
	}

	/**
	 * @param jiwi the jiwi to set
	 */
	public void setJiwi(String jiwi) {
		this.jiwi = jiwi;
	}

	/**
	 * @return the gwansimdo
	 */
	public String getGwansimdo() {
		return gwansimdo;
	}

	/**
	 * @param gwansimdo the gwansimdo to set
	 */
	public void setGwansimdo(String gwansimdo) {
		this.gwansimdo = gwansimdo;
	}

	/**
	 * @return the relation
	 */
	public String getRelation() {
		return relation;
	}

	/**
	 * @param relation the relation to set
	 */
	public void setRelation(String relation) {
		this.relation = relation;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * @param job the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * @return the sdate
	 */
	public String getSdate() {
		return sdate;
	}

	/**
	 * @param sdate the sdate to set
	 */
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	/**
	 * @return the kind
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * @param kind the kind to set
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}

	/**
	 * @return the bigo
	 */
	public String getBigo() {
		return bigo;
	}

	/**
	 * @param bigo the bigo to set
	 */
	public void setBigo(String bigo) {
		this.bigo = bigo;
	}

	/**
	 * @return the friendship
	 */
	public String getFriendship() {
		return friendship;
	}

	/**
	 * @param friendship the friendship to set
	 */
	public void setFriendship(String friendship) {
		this.friendship = friendship;
	}

	/**
	 * @return the corp_nm
	 */
	public String getCorp_nm() {
		return corp_nm;
	}

	/**
	 * @param corp_nm the corp_nm to set
	 */
	public void setCorp_nm(String corp_nm) {
		this.corp_nm = corp_nm;
	}

	/**
	 * @return the tableType
	 */
	public String getTableType() {
		return tableType;
	}

	/**
	 * @param tableType the tableType to set
	 */
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	/**
	 * @return the total_cnt
	 */
	public int getTotal_cnt() {
		return total_cnt;
	}

	/**
	 * @param total_cnt the total_cnt to set
	 */
	public void setTotal_cnt(int total_cnt) {
		this.total_cnt = total_cnt;
	}
}
