

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileInputStream fileInput=new FileInputStream("C:\\Users\\admin\\eclipse-workspace\\DSAProject\\TreeFile");
		ObjectInputStream objectInput=new ObjectInputStream(fileInput);
		String c=request.getParameter("node");
		int node=Integer.parseInt(c);
		AVL Tree=null;
		try {
			Tree=(AVL)objectInput.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		objectInput.close();
		if(AVLImplementation.search(Tree,node))
		{
			AVLImplementation.deleteNode(Tree,node);
			FileOutputStream fileOutput=new FileOutputStream("C:\\\\Users\\\\admin\\\\eclipse-workspace\\\\DSAProject\\\\TreeFile");
			ObjectOutputStream objectOutput=new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(Tree);
			objectOutput.close();
			response.sendRedirect("deleted.html");
		}
		else
			response.sendRedirect("notDeleted.html");
	}

}
