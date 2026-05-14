import java.util.ArrayList;
import java.util.Iterator;

public class ReviewerManager {

    public ArrayList<Reviewer> reviewerList;

    public ArrayList<Reviewer> getAvailableReviewers() {
        reviewerList = new Database().fetchReviewers();

        filterConflicts(reviewerList);
        checkWorkload(reviewerList);

        ArrayList<Reviewer> filteredReviewers = reviewerList;
        return filteredReviewers;
    }

    public void filterConflicts(ArrayList<Reviewer> _reviewerList) {
        Iterator<Reviewer> it = _reviewerList.iterator();
        System.out.println("---------FILTER CONFLICT----------");
        while (it.hasNext()) {
        Reviewer reviewer = it.next();
        System.out.println("{REMOVING}:"+reviewer.reviwerName);
            if (reviewer.hasConflict) {
                it.remove(); // safely remove while iterating
            }
        }
        System.out.println("-----------------------------------");
    }

    public void checkWorkload(ArrayList<Reviewer> _reviewerList) {
    Iterator<Reviewer> it = _reviewerList.iterator();
    System.out.println("-----------FILTERING WORKLOAD-----------");
    while (it.hasNext()) {
        Reviewer reviewer = it.next();
        if (reviewer.isBusy) {
            System.out.println("{REMOVING due to workload}: " + reviewer.reviwerName);
            it.remove(); // safely remove while iterating
        }
    }
    System.out.println("----------------------------------------");
    }
}
