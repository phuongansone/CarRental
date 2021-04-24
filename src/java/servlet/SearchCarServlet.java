package servlet;

import common.CommonAttribute;
import common.RequestMapping.SearchCarRequest;
import common.RequestParam;
import static common.RequestParam.PAGE;
import dto.CarDTO;
import dto.CategoryDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.CarService;
import service.CategoryService;
import util.StringUtil;

/**
 *
 * @author PC
 */
public class SearchCarServlet extends HttpServlet {
    private static final String RECORD_PER_PAGE = "recordPerPage";
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String keyword = request.getParameter(RequestParam.KEYWORD);
        int categoryId = StringUtil.parseInt(
                request.getParameter(RequestParam.CATEGORY_ID), -1);
        
        // get targetted page
        int page = getCurrentPage(request);
        int totalPage;
        int[] pages = null;
        
        List<CarDTO> cars = new ArrayList<>();
        CarService carService = new CarService();
        
        List<CategoryDTO> categories = new ArrayList<>();
        CategoryService categoryService = new CategoryService();
        
        // get number of record per page configuration
        int recordPerPage = Integer.parseInt(request.getServletContext()
                .getInitParameter(RECORD_PER_PAGE));
        int offset = (page - 1) * recordPerPage;
        
        try {
            if (keyword != null && !keyword.isBlank()) {
                totalPage = getTotalNoOfPage(carService.countCarsByName(keyword), recordPerPage);
                pages = getPagesArr(totalPage);
                
                cars = carService.getCarsByName(keyword, offset, recordPerPage);
            } else if (categoryId != -1) {
                totalPage = getTotalNoOfPage(carService.countCarsByCategory(categoryId), recordPerPage);
                pages = getPagesArr(totalPage);
                
                cars = carService.getCarByCategory(categoryId, offset, recordPerPage);
            } else {
                totalPage = getTotalNoOfPage(carService.countAllCars(), recordPerPage);
                pages = getPagesArr(totalPage);

                cars = carService.getAllCars(offset, recordPerPage);                
            }

            categories = categoryService.getAllCategories();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SearchCarServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
            request.getSession()
                    .setAttribute(CommonAttribute.ERROR, Boolean.TRUE);
        }
        
        request.setAttribute(CommonAttribute.PAGE, page);
        request.setAttribute(CommonAttribute.PAGES, pages);
        
        request.setAttribute(CommonAttribute.CARS, cars);
        request.setAttribute(CommonAttribute.CATEGORIES, categories);
        
        request.getRequestDispatcher(SearchCarRequest.VIEW)
                .forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private int getCurrentPage(HttpServletRequest request) {
        return StringUtil.parseInt(request.getParameter(PAGE), 1);
    }
    
    private int[] getPagesArr(int totalPage) {
        if (totalPage <= 0) {
            return new int[0];
        }
        
        int[] pages = new int[totalPage];
        
        for (int i = 0; i < totalPage; i++) {
            pages[i] = i + 1;
        }
        return pages;
    }
    
    private int getTotalNoOfPage(int totalRecords, int recordPerPage) {
        double totalPage = (double)totalRecords / (double) recordPerPage;
        return (int)Math.ceil(totalPage);
    }
}
