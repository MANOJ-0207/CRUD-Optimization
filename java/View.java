

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class View
 */
@WebServlet("/View")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String inOrderOP;
	static String postOrderOP;
	static String preOrderOP;
	static String levelString;
	static ArrayList<String>levelOrderList=new ArrayList<String>();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public View() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter printer=response.getWriter();
		FileInputStream fileInput=new FileInputStream("C:\\Users\\admin\\eclipse-workspace\\DSAProject\\TreeFile");
		ObjectInputStream objectInput=new ObjectInputStream(fileInput);
		AVL Tree=null;
		try {
			Tree=(AVL)objectInput.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		objectInput.close();
		inOrderOP="";
		preOrderOP="";
		postOrderOP="";
		inOrder(Tree);
		preOrder(Tree);
		postOrder(Tree);
		response.setContentType("text/html");
		printer.write("<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\r\n"
				+ "        integrity=\"sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD\" crossorigin=\"anonymous\">\r\n"
				+ "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js\"\r\n"
				+ "        integrity=\"sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN\"\r\n"
				+ "        crossorigin=\"anonymous\"></script>\r\n"
				+ "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css\">\r\n"
				+ "    <link rel=\"stylesheet\" href=\"index.css\">\r\n"
				+ "    <link rel=\"shortcut icon\" href=\"\" type=\"image/x-icon\">\r\n"
				+ "    <title>Deletion Optimization</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <a href=\"index.html\"><button type=\"button\" class=\"btn btn-secondary btn-lg btn-back\">Back</button></a><h1 class=\"heading\">Deletion Optimization</h1>\r\n"
				+ "    <h2 class=\"heading\">AVL TREE <i class=\"bi bi-diagram-2-fill\"></i></h2>\r\n"
				+ "    <h2 class=\"heading-small\">Tree Structure <i class=\"bi bi-diagram-2-fill\"></i> </h3>\r\n"
				+ " <h4 class=\"heading-small\" >PRE  ORDER : "+preOrderOP+"</h2>\n"
				+ " <h4 class=\"heading-small\" >IN   ORDER : "+inOrderOP+"</h2>\n"
				+ " <h4 class=\"heading-small\" >POST ORDER : "+postOrderOP+"</h2>\n"
				+ " <h4 class=\"heading-small\" >LEVEL ORDER : <br>");
		levelOrderList.clear();
		levelString="";
		printLevelOrder(Tree);
		for(String ls:levelOrderList)
			printer.write(ls+"<br>");
		printer.write(
				"</body>\r\n"
				+ "</html>");
	}
	public static void inOrder(AVL Tree)
	{
		if(Tree==null)
			return;
		inOrder(Tree.left);
		inOrderOP+=Tree.key+" ";
		inOrder(Tree.right);
	}
	public static void postOrder(AVL Tree)
	{
		if(Tree==null)
			return;
		postOrder(Tree.left);
		postOrder(Tree.right);
		postOrderOP+=Tree.key+" ";
	}
	public static void preOrder(AVL Tree)
	{
		if(Tree==null)
			return;
		preOrderOP+=Tree.key+" ";
		preOrder(Tree.left);
		preOrder(Tree.right);
	}
	public static void printLevelOrder(AVL root)
	{
		int height=AVLImplementation.height(root);
		for(int i=0;i<=height;i++)
		{
			levelString="";
			printLevel(root,i);
			levelOrderList.add(levelString);
		}
	}
	public static void printLevel(AVL root,int level)
	{
		if(root==null)
			return;
		if(level==0)
			levelString+=root.key+" ";
		else
		{
			printLevel(root.left,level-1);
			printLevel(root.right,level-1);
		}
	}

}
