
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Assemble {
	public static void main(String[] args) throws IOException {
		Map <String, Integer> JVMsubsetTable = new HashMap<String, Integer>();
		JVMsubsetTable.put("label", 0);
		JVMsubsetTable.put("print", 1);
		JVMsubsetTable.put("println", 2);
		JVMsubsetTable.put("iconst_m1", 2);
		JVMsubsetTable.put("iconst_0", 4);
		JVMsubsetTable.put("iconst_1", 3);
		JVMsubsetTable.put("iconst_2", 5);
		JVMsubsetTable.put("iconst_3", 6);
		JVMsubsetTable.put("iconst_4", 7);
		JVMsubsetTable.put("iconst_5", 8);
		JVMsubsetTable.put("bipush", 16);
		JVMsubsetTable.put("iload", 21);
		JVMsubsetTable.put("iload_0", 26);
		JVMsubsetTable.put("iload_1", 27);
		JVMsubsetTable.put("iload_2", 28);
		JVMsubsetTable.put("iload_3", 29);
		JVMsubsetTable.put("istore_0", 59);
		JVMsubsetTable.put("istore_1", 60);
		JVMsubsetTable.put("istore_2", 61);
		JVMsubsetTable.put("istore_3", 62);
		JVMsubsetTable.put("istore", 54);
		JVMsubsetTable.put("pop", 87);
		JVMsubsetTable.put("swap", 95);
		JVMsubsetTable.put("dup", 89);
		JVMsubsetTable.put("iadd", 96);
		JVMsubsetTable.put("isub", 100);
		JVMsubsetTable.put("imul", 104);
		JVMsubsetTable.put("idiv", 108);
		JVMsubsetTable.put("irem", 112);
		JVMsubsetTable.put("ineg", 116);
		JVMsubsetTable.put("iinc", 132);
		JVMsubsetTable.put("ifeq", 153);
		JVMsubsetTable.put("ifge", 156);
		JVMsubsetTable.put("ifgt", 157);
		JVMsubsetTable.put("ifle", 158);
		JVMsubsetTable.put("ifne", 154);
		JVMsubsetTable.put("goto", 167);
		JVMsubsetTable.put("jsr", 168);
		JVMsubsetTable.put("ret", 169);
		JVMsubsetTable.put("return", 177);
		JVMsubsetTable.put("invokevirtual", 182);

		Map <String, Integer> JVMsubsetTable_Inc = new HashMap<String, Integer>();
		JVMsubsetTable_Inc.put("print", 2);
		JVMsubsetTable_Inc.put("println", 2);
		JVMsubsetTable_Inc.put("iconst_m1", 1);
		JVMsubsetTable_Inc.put("iconst_0", 1);
		JVMsubsetTable_Inc.put("iconst_1", 1);
		JVMsubsetTable_Inc.put("iconst_2", 1);
		JVMsubsetTable_Inc.put("iconst_3", 1);
		JVMsubsetTable_Inc.put("iconst_4", 1);
		JVMsubsetTable_Inc.put("iconst_5", 1);
		JVMsubsetTable_Inc.put("bipush", 2);
		JVMsubsetTable_Inc.put("iload", 2);
		JVMsubsetTable_Inc.put("iload_0", 1);
		JVMsubsetTable_Inc.put("iload_1", 1);
		JVMsubsetTable_Inc.put("iload_2", 1);
		JVMsubsetTable_Inc.put("iload_3", 1);
		JVMsubsetTable_Inc.put("istore_0", 1);
		JVMsubsetTable_Inc.put("istore_1", 1);
		JVMsubsetTable_Inc.put("istore_2", 1);
		JVMsubsetTable_Inc.put("istore_3", 1);
		JVMsubsetTable_Inc.put("istore", 2);
		JVMsubsetTable_Inc.put("pop", 1);
		JVMsubsetTable_Inc.put("swap", 1);
		JVMsubsetTable_Inc.put("dup", 1);
		JVMsubsetTable_Inc.put("iadd", 1);
		JVMsubsetTable_Inc.put("isub", 1);
		JVMsubsetTable_Inc.put("imul", 1);
		JVMsubsetTable_Inc.put("idiv", 1);
		JVMsubsetTable_Inc.put("irem", 1);
		JVMsubsetTable_Inc.put("ineg", 1);
		JVMsubsetTable_Inc.put("iinc", 3);
		JVMsubsetTable_Inc.put("ifeq", 2);
		JVMsubsetTable_Inc.put("ifge", 2);
		JVMsubsetTable_Inc.put("ifgt", 2);
		JVMsubsetTable_Inc.put("ifle", 2);
		JVMsubsetTable_Inc.put("ifne", 2);
		JVMsubsetTable_Inc.put("goto", 2);
		JVMsubsetTable_Inc.put("jsr", 2);
		JVMsubsetTable_Inc.put("ret", 1);
		JVMsubsetTable_Inc.put("return", 1);
		JVMsubsetTable_Inc.put("invokevirtual", 2);

		String lbl = "label";
		String gt = "goto";
		String pf = "(";
		String pl = ")";
		String c = ",";
		String com = "comment";
		Map <String, Integer> OPtable = new HashMap<String, Integer>();

		/***************************** Pass One ***********************************/
		System.out.println("Pass One: ");
		System.out.println("Location\tSymbol");
		System.out.println("-------------------------");
		String line;
		int loc_count = 0;
		FileReader file = new FileReader("test.txt");
		BufferedReader reader = new BufferedReader(file);

		while((line=reader.readLine()) != null){
			String var_in_command = null;
			String command = null;
			String current_line = line.replaceAll("\\s+", "");//remove all spaces
			boolean fin = false;
			int p1 = current_line.indexOf(pf);
			int p2 = current_line.indexOf(pl);
			int c1 = current_line.indexOf(com);

			if(!current_line.isEmpty()&&!fin){
				//What's left is: "code"
				//"code(variable)", "code(variable, variable)"						
				if(c1==0){
					//Only Comments
					//do nothing... it's a comment on space 0
					//System.out.println("Skipping comment: "+current_line);
				} else if (c1==-1){ 
					//there are no comments in line

					if(current_line.contains(pf)){ //Check for variables
						String var1 = current_line.substring(0,p1);   // "goto(main)", goto savaed
						String var2 = current_line.substring(p1+1,p2);// "goto(manin)", main saved
						if(var1.contains(lbl)){ //Is it a label?
							System.out.println(loc_count+"\t\t"+var2+"\t"+var1);
						}
						if(JVMsubsetTable_Inc.containsKey(var1))
							loc_count+=JVMsubsetTable_Inc.get(var1);


					} else if (JVMsubsetTable.containsKey(current_line)) {
						//System.out.print(loc_count+"\t\t");
						if(JVMsubsetTable_Inc.containsKey(current_line))
							loc_count+=JVMsubsetTable_Inc.get(current_line);
						//System.out.println(current_line);
					}

				} else {
					String uncomt_line=current_line.substring(0,c1);
					if(uncomt_line.contains(pf)){
						//System.out.print(loc_count+"\t\t");
						int p3 = current_line.indexOf(pf);
						int p4 = current_line.indexOf(pl);
						String var1 = current_line.substring(0,p3);   // "goto(main)", goto savaed
						String var2 = current_line.substring(p3+1,p4);// "goto(manin)", main saved
						if(JVMsubsetTable_Inc.containsKey(var1))
							loc_count+=JVMsubsetTable_Inc.get(var1);

					} else {
						//System.out.print(loc_count+"\t\t");
						if(JVMsubsetTable_Inc.containsKey(uncomt_line))
							loc_count+=JVMsubsetTable_Inc.get(uncomt_line);
						//System.out.println(uncomt_line); //removed the comment and kept code
					}
				}

			}
		}//end while loop

		/***************************** Pass Two ***********************************/
		FileReader file2 = new FileReader("test.txt");
		BufferedReader reader2 = new BufferedReader(file2);
		String line2;
		System.out.println("\n"+"Pass Two: ");
		System.out.println("Address |\t Opcode | Stack");
		System.out.println("-------------------------------------");
		int loc_count2 = 0;

		while((line2=reader2.readLine()) != null){
			String var_in_command = null;
			String command = null;
			String c_l = line2.replaceAll("\\s+", "");//remove all spaces
			boolean fin = false;
			int p1 = c_l.indexOf(pf);
			int p2 = c_l.indexOf(pl);
			int c1 = c_l.indexOf(com);

			if(!c_l.isEmpty()&&!fin){
				//What's left is: "code"
				//"code(variable)", "code(variable, variable)"						
				if(c1==0){
					//Only Comments
					//do nothing... it's a comment on space 0
					//System.out.println("Skipping comment: "+current_line);
				} else if (c1==-1){ 
					//there are no comments in line

					if(c_l.contains(pf)){ //Check for variables
						String var1 = c_l.substring(0,p1);   // "goto(main)", goto savaed
						String var2 = c_l.substring(p1+1,p2);// "goto(manin)", main saved
						int opCode = JVMsubsetTable.get(var1);
						System.out.println(loc_count2+"\t\t"+opCode);
						if(JVMsubsetTable_Inc.containsKey(var1))
							loc_count2+=JVMsubsetTable_Inc.get(var1);


					} else if (JVMsubsetTable.containsKey(c_l)) {
						System.out.print(loc_count2+"\t\t");
						if(JVMsubsetTable_Inc.containsKey(c_l))
							loc_count2+=JVMsubsetTable_Inc.get(c_l);
						int opCode = JVMsubsetTable.get(c_l);
						System.out.println(opCode);
					}

				} else {
					String uncomt_line=c_l.substring(0,c1);
					if(uncomt_line.contains(pf)){
						System.out.print(loc_count2+"\t\t");
						int p3 = c_l.indexOf(pf);
						int p4 = c_l.indexOf(pl);
						String var1 = c_l.substring(0,p3);   // "goto(main)", goto savaed
						String var2 = c_l.substring(p3+1,p4);// "goto(manin)", main saved
						int opCode = JVMsubsetTable.get(var1);
						if(JVMsubsetTable_Inc.containsKey(var1))
							loc_count2+=JVMsubsetTable_Inc.get(var1);
						System.out.println(opCode+"\t"+var2); //removed the comment and kept code

					} else {
						System.out.print(loc_count2+"\t\t");
						if(JVMsubsetTable_Inc.containsKey(uncomt_line))
							loc_count2+=JVMsubsetTable_Inc.get(uncomt_line);
						int opCode = JVMsubsetTable.get(uncomt_line);
						System.out.println(opCode); //removed the comment and kept code
					}
				}

			}
		}//end while loop
	}
}