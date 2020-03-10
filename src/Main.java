//全局类，储存实用方法验证
class Global {
	public static boolean isNaN(String str) {
		int yes = 0, no = 0;
		for(int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				yes++;
			}else{
				no++;
			}
		}
		if(yes >= no) {
			return true;
		}
		return false;
	}
}

public class Main {
	//判断是否已经按了计算按钮
	static boolean hasnb = false;

	public static void main(String[] args) {
		Gui gui = new Gui();
		gui.Init();

//		Operate op = new Operate();
//		
//		
//		System.err.println(op.getAlgorithm("-").Compute(1, 3));
	}
	
	//检查输入是否合法
	public static boolean checkF(String txtstr, String acceptstr) {

		if(txtstr.length() == 0 && !Global.isNaN(acceptstr)) {
			if(!acceptstr.equals("-")) {
				return false;
			}
		}
		if(txtstr.length() != 0) {
			char lstreg = txtstr.charAt(txtstr.length() - 1);
			String lststr = String.valueOf(lstreg);

            if(!acceptstr.equals("C")) {

				//检查右括号是否合法输入
                if(acceptstr.equals(")")) {
                    int bk_count = 0;
                    for(int i = 0; i < txtstr.length(); i++) {
                        if(txtstr.charAt(i) == '(') {
                            bk_count++;
                        }else if(txtstr.charAt(i) == ')') {
                            bk_count--;
                        }
                    }
                    if(bk_count == 0) {
                        return false;
                    }
                }

				//检查.号是否合法
				if(acceptstr.equals(".")) {
					int com_count = 0;
					//遍历txtstr查找.符号然后标记
					for(int i = 0; i < txtstr.length(); i++) {
						if(txtstr.charAt(i) == '.') {
							com_count++;
						}
						//遇到除了.号的符号则重置可继续输入.
						if(!Global.isNaN(txtstr.charAt(i) + "") && txtstr.charAt(i) != '.') {
							com_count = 0;
						}
					}
					
					//第一次按.  txtstr长度不够不能遍历找出，先自加
					com_count++;

					if(com_count > 1) {
						return false;
					}
				}

            }

			//验证加减乘除是否合法
			if(lststr.equals("+") || lststr.equals("-") || lststr.equals("*") || lststr.equals("/") || lststr.equals("^")) {
				if(!Global.isNaN(acceptstr) && !acceptstr.equals("(") && !acceptstr.equals("C")) {
					return false;
				}
			}
		}

		return true;
	}

	//按钮事件
	public static String buttoneven(String txtstr, String acceptstr) {
		//计算后按数字键自动清理得数
		if(Global.isNaN(acceptstr) && hasnb) {
			hasnb = false;
			return "";
		}else if(!Global.isNaN(acceptstr) && hasnb){
			hasnb = false;
		}

		//检测除数是否为0
		if(txtstr.length() != 0 && txtstr.indexOf("/0") != -1) {
			hasnb = true;
			return "除数不能为零";
		}
		//按了=号，并且txtstr不为空
		if(acceptstr.equals("=") && !txtstr.isEmpty()) {
			hasnb = true;
			return Calculator.count(txtstr) + "";
		}
		if(acceptstr == "C") {
			return "";
		}
		
		return "-1";
	}
}
