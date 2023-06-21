package by.belstu.lab10_checkboxes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "ResultsServlet", value = "/ResultsServlet")
public class ResultsServlet extends HttpServlet {
    private static final DAO db = new DAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultSet rs = db.ExecuteQuery("select * from presidents");
        List<Candidate> candidates = new ArrayList<>();
        List<Candidate> chosenCandidates = new ArrayList<>();
        try {
            while (rs.next()) {
                candidates.add(new Candidate(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String id[] = request.getParameterValues("checkboxes");
        if (id != null) {
            for (var i : id) {
                int candidateId = Integer.parseInt(i);
                Optional<Candidate> candidate = candidates.stream().filter(c -> c.getId() == candidateId).findFirst();
                if (candidate.isPresent()) {
                    db.ExecuteQuery("update presidents set count_of_votes = "
                            + (candidate.get().getNumberOfVotes() + 1) + " where id = " + candidate.get().getId());
                    candidate.get().setNumberOfVotes(candidate.get().getNumberOfVotes() + 1);
                    chosenCandidates.add(candidate.get());
                }
            }
        }
        int allVotes = 0;
        for (var c: candidates) {
            allVotes += c.getNumberOfVotes();
        }
        request.setAttribute("allVotes", allVotes);
        request.setAttribute("chosenCandidates", chosenCandidates);
        request.getRequestDispatcher("resultPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
