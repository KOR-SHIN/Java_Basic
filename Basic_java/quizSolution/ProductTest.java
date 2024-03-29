package academy;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class ProductTest {

	public static void main(String[] args) {

		Vector productList = new Vector();
		Vector customerList = new Vector();

		ProductVO prod_1 = new ProductVO();
		prod_1.setProd_id("A0001");
		prod_1.setProd_price(300);
		prod_1.setProd_name("MacPro Retina");
		prod_1.setProd_mileage(prod_1.getProd_price() / 10);
		prod_1.setProd_stock(5);
		productList.add(prod_1);

		ProductVO prod_2 = new ProductVO();
		prod_2.setProd_id("A0002");
		prod_2.setProd_price(150);
		prod_2.setProd_name("LG Styler");
		prod_2.setProd_mileage(prod_2.getProd_price() / 10);
		prod_2.setProd_stock(5);
		productList.add(prod_2);

		ProductVO prod_3 = new ProductVO();
		prod_3.setProd_id("A0003");
		prod_3.setProd_price(600);
		prod_3.setProd_name("LG Signature Friger");
		prod_3.setProd_mileage(prod_3.getProd_price() / 10);
		prod_3.setProd_stock(5);
		productList.add(prod_3);

		ProductVO prod_4 = new ProductVO();
		prod_4.setProd_id("A0004");
		prod_4.setProd_price(240);
		prod_4.setProd_name("SAMSUNG FlexBook");
		prod_4.setProd_mileage(prod_4.getProd_price() / 10);
		prod_4.setProd_stock(3);
		productList.add(prod_4);

		ProductVO prod_5 = new ProductVO();
		prod_5.setProd_id("A0005");
		prod_5.setProd_price(300);
		prod_5.setProd_name("Apple AirPod Pro");
		prod_5.setProd_mileage(prod_5.getProd_price() / 10);
		prod_5.setProd_stock(0);
		productList.add(prod_5);

		CustomerVO member_1 = new CustomerVO();
		member_1.setMem_add("대전광역시");
		member_1.setMem_budget(1000000);
		member_1.setMem_hp("010-5361-3333");
		member_1.setMem_id("shinkwang23");
		member_1.setMem_job("Student");
		member_1.setMem_mileage(0);
		member_1.setMem_pw("java");
		member_1.setMem_regno1("950130");
		member_1.setMem_regno2("1XXXXXX");
		member_1.setMem_name("신광진");
		customerList.add(member_1);

		Service service = new Service();

		Scanner scan = new Scanner(System.in);
		int command = 0;
		
		while (command < 6) {

			System.out.println("===================");
			System.out.println(" ** Welcome !! **");
			System.out.println("===================");

			System.out.println("번호를 입력해주세요.");
			System.out.println("1. 물품구매하기");
			System.out.println("2. 영수증 출력하기");
			System.out.println("3. 환불하기");
			System.out.println("4. 구매이력 확인하기");
			System.out.println("5. 환불이력 확인하기");
			System.out.println("6. 쇼핑종료");

			command = scan.nextInt();
			scan.nextLine();
			
			switch (command) {

			case 1:

				if (productList.isEmpty()) {
					System.out.println("모든 상품이 품절되었습니다.");
					break;
				}

				System.out.println("======구매가능 상품목록======");
				for (int i = 0; i < productList.size(); i++) {

					if (((ProductVO) productList.get(i)).getProd_stock() < 1) {
						// 재고가 없는 상품은 출력하지 않는다.
						continue;
					}

					System.out.println("상품명 : " + ((ProductVO) productList.get(i)).getProd_name() + " / 가격 : "
							+ ((ProductVO) productList.get(i)).getProd_price());
				}

				System.out.print("구매 상품명 : ");
				String buyCommand = scan.nextLine().toUpperCase().replace(" ", "");
				ProductVO buyProd = null;

				for (int i = 0; i < productList.size(); i++) {
					if (((ProductVO) productList.get(i)).getProd_name().toUpperCase().replace(" ", "").equals(buyCommand)) {
						buyProd = ((ProductVO) productList.get(i));
						break;
					}
				}
				if (buyProd == null) {
					System.out.println("입력하신 상품이름과 동일한 상품이 존재하지 않습니다.");
					break;
				}

				service.buy(buyProd, member_1);
				break;

			case 2:
				if (member_1.getBuyList().isEmpty()) {
					System.out.println("구매목록이 없습니다.");
					break;
				}

				service.summary(member_1);
				break;

			case 3:
				if (member_1.getBuyList().isEmpty()) {
					System.out.println("구매목록이 없습니다.");
					break;
				}

				System.out.println("======" + member_1.getMem_name() + "고객님의 보유상품======");
				for (int i = 0; i < member_1.getBuyList().size(); i++) {
					System.out.println(((ProductVO) member_1.getBuyList().get(i)).getProd_name());
				}
				System.out.println("환불요청 상품명 : ");

				String rmCommand = scan.nextLine().toUpperCase().replace(" ", "");
				ProductVO rmProd = null;

				for (int i = 0; i < productList.size(); i++) {
					if (((ProductVO) productList.get(i)).getProd_name().toUpperCase().replace(" ", "")
							.equals(rmCommand)) {
						rmProd = (ProductVO) productList.get(i);
					}
				}

				service.refund(rmProd, member_1);
				break;
				
			case 4:
				if(member_1.getBuyHistory().isEmpty()) {
					System.out.println("구매이력이 없습니다");
					break;
				}
				System.out.println("======" + member_1.getMem_name() + "회원님의 구매 이력======");

				for(Object item : member_1.getBuyHistory()) {
					System.out.println("상품명 : " + ((ProductVO)item).getProd_name() + 
										"(" +((ProductVO)item).getProd_price() + "만원)");
				}
				break;
			
			case 5:
				if(member_1.getRefundList().isEmpty()) {
					System.out.println("환불이력이 없습니다.");
					break;
				}
				
				System.out.println("======" + member_1.getMem_name() + "회원님의 환불 이력======");
				for(Object item : member_1.getRefundList()) {
					System.out.println("환불 상품명 : " + ((ProductVO)item).getProd_name() + 
										"(" +((ProductVO)item).getProd_price() + "만원)");
				}
				break;

			default:
				System.out.println("======쇼핑을 종료합니다.======");
				System.out.println("감사합니다 고객님 좋은하루 되십시오.");
				break;
			}
		}

	}
}

class Service {

	void buy(ProductVO prod, CustomerVO buyer) {

		if (prod.getProd_price() > buyer.getMem_budget()) {
			// 고객 보유금액 조회
			System.out.println("보유금액이 부족합니다.");
			System.out.println(prod.getProd_name() + "고객님의 현재 보유금액은 " + buyer.getMem_budget() + "만원 입니다.");
			System.out.println("[" + prod.getProd_name() + "상품 가격 : " + prod.getProd_price() + "만원]");
			return;
		}

		if (prod.getProd_stock() < 1) {
			System.out.println(prod.getProd_name() + "상품은 품절되었습니다.");
			return;
		}

		buyer.setMem_mileage(buyer.getMem_mileage() + prod.getProd_mileage());
		buyer.setMem_budget(buyer.getMem_budget() - prod.getProd_price());
		manageBuyList(prod, buyer, "add");
		manageBuyHistory(prod, buyer);
		prod.setProd_stock(prod.getProd_stock() - 1);

		System.out.println(buyer.getMem_name() + "고객님" + prod.getProd_name() + "를 구매해주셔서 감사합니다.");
		System.out.println("현재 회원금의 보유금액은 " + buyer.getMem_budget() + "만원 입니다.");

	}

	void summary(CustomerVO buyer) {

		if (buyer.getBuyList().isEmpty()) {
			System.out.println(buyer.getMem_name() + "고객님의 구매목록이 존재하지 않습니다.");
			return;
		}

		System.out.println("\t영\t수\t증");
		System.out.println("구매목록");
		int total = 0;

		for (int i = 0; i < buyer.getBuyList().size(); i++) {

			System.out.println("\t" + ((ProductVO) buyer.getBuyList().get(i)).getProd_name() + " : "
					+ ((ProductVO) buyer.getBuyList().get(i)).getProd_price());
			total += ((ProductVO) buyer.getBuyList().get(i)).getProd_price();
		}

		System.out.println(
				buyer.getMem_name() + "고객님의 총 구매금액은 " + total + "만원이고, 보유마일리지는 " + buyer.getMem_mileage() + "만원 입니다.");
		System.out.println("오늘도 좋은하루 보내십시오.");
	}

	void refund(ProductVO prod, CustomerVO buyer) {

		for (int i = 0; i < buyer.getBuyList().size(); i++) {

			if (((ProductVO) buyer.getBuyList().get(i)).getProd_id() == prod.getProd_id()) {
				buyer.setMem_budget(buyer.getMem_budget() + prod.getProd_price());
				buyer.setMem_mileage(buyer.getMem_mileage() - prod.getProd_mileage());
				manageRefundList(prod, buyer);
				manageBuyList(prod, buyer, "remove");
				System.out.println(buyer.getMem_name() + "고객님이 요청하신 " + prod.getProd_name() + "상품의 환불이 완료되었습니다.");
				break;
			}
		}
	}

	void manageBuyList(ProductVO prod, CustomerVO buyer, String command) {

		Vector newList = new Vector(buyer.getBuyList());
		
		if (command.equals("add")) {
			newList.add(prod);
	
		} else if(command.equals("remove")){
			newList.remove(prod);
		}
		
		buyer.setBuyList(newList);
	}
	
	void manageRefundList(ProductVO prod, CustomerVO buyer) {
		Vector newList = new Vector(buyer.getRefundList());
		newList.add(prod);
		
		buyer.setRefundList(newList);
	}
	
	void manageBuyHistory(ProductVO prod, CustomerVO buyer) {
		Vector newList = new Vector(buyer.getBuyHistory());
		newList.add(prod);
		
		buyer.setBuyHistory(newList);
	}

}

class ProductVO {

	private String prod_id;
	private String prod_name;
	private int prod_price;
	private int prod_mileage;
	private int prod_stock;

	public int getProd_stock() {
		return prod_stock;
	}

	public void setProd_stock(int prod_stock) {
		this.prod_stock = prod_stock;
	}

	public String getProd_id() {
		return prod_id;
	}

	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public int getProd_price() {
		return prod_price;
	}

	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}

	public int getProd_mileage() {
		return prod_mileage;
	}

	public void setProd_mileage(int prod_mileage) {
		this.prod_mileage = prod_mileage;
	}

}

class CustomerVO {

	private String mem_id;
	private String mem_pw;
	private String mem_name;
	private String mem_regno1;
	private String mem_regno2;
	private String mem_add;
	private String mem_hp;
	private String mem_job;
	private int mem_budget;
	private int mem_mileage;
	private Vector buyList = new Vector();
	private Vector refundList = new Vector();
	private Vector buyHistory = new Vector();
	
	public Vector getBuyHistory() {
		return buyHistory;
	}

	public void setBuyHistory(Vector buyHistory) {
		this.buyHistory = buyHistory;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_pw() {
		return mem_pw;
	}

	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_regno1() {
		return mem_regno1;
	}

	public void setMem_regno1(String mem_regno1) {
		this.mem_regno1 = mem_regno1;
	}

	public String getMem_regno2() {
		return mem_regno2;
	}

	public void setMem_regno2(String mem_regno2) {
		this.mem_regno2 = mem_regno2;
	}

	public String getMem_add() {
		return mem_add;
	}

	public void setMem_add(String mem_add) {
		this.mem_add = mem_add;
	}

	public String getMem_hp() {
		return mem_hp;
	}

	public void setMem_hp(String mem_hp) {
		this.mem_hp = mem_hp;
	}

	public String getMem_job() {
		return mem_job;
	}

	public void setMem_job(String mem_job) {
		this.mem_job = mem_job;
	}

	public int getMem_budget() {
		return mem_budget;
	}

	public void setMem_budget(int mem_budget) {
		this.mem_budget = mem_budget;
	}

	public int getMem_mileage() {
		return mem_mileage;
	}

	public void setMem_mileage(int mem_mileage) {
		this.mem_mileage = mem_mileage;
	}

	public Vector getBuyList() {
		return buyList;
	}

	public void setBuyList(Vector buyList) {
		this.buyList = (Vector) buyList.clone();
	}

	public Vector getRefundList() {
		return refundList;
	}

	public void setRefundList(Vector refundList) {
		this.refundList = refundList;
	}

}