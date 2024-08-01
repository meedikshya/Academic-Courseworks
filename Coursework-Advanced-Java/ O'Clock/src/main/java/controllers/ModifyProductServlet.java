package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import configs.DbConnectionConfig;
import models.ProductModel;
import services.ProductServices;
import utils.StringUtilsProduct;

/**
 * Servlet implementation class ModifyProductServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = StringUtilsProduct.SERVLET_URL_MODIFY_USER)
public class ModifyProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DbConnectionConfig dbObj;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyProductServlet() {
    	 this.dbObj = new DbConnectionConfig();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String updateId = request.getParameter(StringUtilsProduct.UPDATE_ID);
		String deleteId = request.getParameter(StringUtilsProduct.DELETE_ID);
		System.out.println("post triggered" + deleteId);

		if (updateId != null && !updateId.isEmpty()) {
			doPut(request, response);
		}
		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
		}
	}

@Override
protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	System.out.println("put triggered");
	String name = req.getParameter(StringUtilsProduct.UPDATE_ID);
	 ProductModel products = ProductServices.getProductByName(name);

	req.setAttribute("product", products);
	req.getRequestDispatcher("/pages/modifyPage.jsp").forward(req, resp);

}


@Override
protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	System.out.println("delete triggered");
	try {
		if (ProductServices.deleteProduct(req.getParameter(StringUtilsProduct.DELETE_ID)) == 1) {
			req.setAttribute(StringUtilsProduct.MESSAGE_SUCCESS, StringUtilsProduct.MESSAGE_SUCCESS_DELETE);
				resp.sendRedirect(req.getContextPath() + StringUtilsProduct.SERVLET_URL_ADD_PRODUCT);
			} else {
				req.setAttribute(StringUtilsProduct.MESSAGE_ERROR, StringUtilsProduct.MESSAGE_ERROR);
				resp.sendRedirect(req.getContextPath() + StringUtilsProduct.SERVLET_URL_ADD_PRODUCT);
			}
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
