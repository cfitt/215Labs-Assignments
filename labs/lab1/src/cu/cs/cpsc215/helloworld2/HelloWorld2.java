package cu.cs.cpsc215.helloworld2;

public class HelloWorld2 {
	public static void main(String[] args) {
		
		System.out.println("Hello World!!");
		
		int i;
        for(i=0 ; i<args.length; ++i)
        {
            System.out.println(args[i]);
        }
// or one can do
        /*
		for (String s : args)
        {
            System.out.println(s);
        }
        */
	}
}