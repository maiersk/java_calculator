//ȫ���࣬����ʵ�÷�����֤
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
	//�ж��Ƿ��Ѿ����˼��㰴ť
	static boolean hasnb = false;

	public static void main(String[] args) {
		Gui gui = new Gui();
		gui.Init();

//		Operate op = new Operate();
//		
//		
//		System.err.println(op.getAlgorithm("-").Compute(1, 3));
	}
	
	//��������Ƿ�Ϸ�
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

				//����������Ƿ�Ϸ�����
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

				//���.���Ƿ�Ϸ�
				if(acceptstr.equals(".")) {
					int com_count = 0;
					//����txtstr����.����Ȼ����
					for(int i = 0; i < txtstr.length(); i++) {
						if(txtstr.charAt(i) == '.') {
							com_count++;
						}
						//��������.�ŵķ��������ÿɼ�������.
						if(!Global.isNaN(txtstr.charAt(i) + "") && txtstr.charAt(i) != '.') {
							com_count = 0;
						}
					}
					
					//��һ�ΰ�.  txtstr���Ȳ������ܱ����ҳ������Լ�
					com_count++;

					if(com_count > 1) {
						return false;
					}
				}

            }

			//��֤�Ӽ��˳��Ƿ�Ϸ�
			if(lststr.equals("+") || lststr.equals("-") || lststr.equals("*") || lststr.equals("/") || lststr.equals("^")) {
				if(!Global.isNaN(acceptstr) && !acceptstr.equals("(") && !acceptstr.equals("C")) {
					return false;
				}
			}
		}

		return true;
	}

	//��ť�¼�
	public static String buttoneven(String txtstr, String acceptstr) {
		//��������ּ��Զ��������
		if(Global.isNaN(acceptstr) && hasnb) {
			hasnb = false;
			return "";
		}else if(!Global.isNaN(acceptstr) && hasnb){
			hasnb = false;
		}

		//�������Ƿ�Ϊ0
		if(txtstr.length() != 0 && txtstr.indexOf("/0") != -1) {
			hasnb = true;
			return "��������Ϊ��";
		}
		//����=�ţ�����txtstr��Ϊ��
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
