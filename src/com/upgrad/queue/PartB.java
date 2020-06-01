package com.upgrad.queue;

import java.util.LinkedList;
import java.util.Queue;

class SiteStats {
    private String url;
    private int numVisits;

    /**
     * Constructor for the SiteStats class
     *
     * @param url
     *            String that represents an URL that the user has visited
     * @param numVisits
     *            An int that represents the number of times that the user has
     *            visited the url
     */
    public SiteStats(String url, int numVisits) {
        this.url = url;
        this.numVisits = numVisits;
    }

    /**
     * This method returns the number of times that the user has visited the url.
     *
     * @return An int that represents the number of times that the user has visited
     *         the url
     */
    public int getNumVisits() {
        return this.numVisits;
    }

    /**
     * This method returns the url that we are currently tracking
     *
     * @return A String that represents the url that we are currently tracking
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * This method updates the number of times that we have visited the url
     *
     * @param an
     *            int that represents the number that we want to set numVisits to
     */
    public void setNumVisits(int updatedNumVisits) {
        this.numVisits = updatedNumVisits;
    }

    public String toString() {
        return this.url + " | " + this.numVisits;
    }

}

public class PartB {

    private static Queue<SiteStats> sites = new LinkedList<SiteStats>();
    /**
     * This method finds the most visited site in the queue size endIndex
     * @param sites: the queue of SiteStats objects
     * @param endIndex: the integer end index till the processing needs to be done
     * @return the most visited SiteStats node
     */
    public static SiteStats findMostVisited(Queue<SiteStats> sites, int endIndex) {
        // Initializing maxVisits as lowest element
        int maxVisits = -1;
        // Initializing the mostVisited node in the queue
        SiteStats mostVisited = null;
        // Looping over the queue to find the most visited site till endIndex
        for (int i = 0; i < sites.size(); i++) {
            SiteStats head = sites.remove();
            // Checking if number of visits for this site is greater than maxVisits
            // and the site has not already been processed before by ensuring that
            // index is less than endIndex
            if ((head.getNumVisits() >= maxVisits) && (i < endIndex)) {
                mostVisited = head;
                maxVisits = head.getNumVisits();
            }
            // Inserting the processed site back towards the end, so that this operation
            // does not modify the existing ordering in the list
            sites.add(head);
        }

        return mostVisited;

    }
    /**
     * This method reorders the queue to place the mostVisited site at the rear end of the queue
     * @param sites: the queue of SiteStats objects
     * @param mostVisited: the most visited SiteStats node in the queue
     */
    public static void reorder(Queue<SiteStats> sites, SiteStats mostVisited) {
        for (int i = 0; i <= sites.size(); i++) {
            SiteStats head = sites.remove();
            // If the element is not the recently processed mostVisited site, then insert it
            // again in the rear end
            if (mostVisited != head) {
                sites.add(head);
            }
        }
        // Insert the recently processed mostVisited site in the rear end
        sites.add(mostVisited);
    }
    /**
     * This method lists the top n visited sites
     * @param sites: the queue of SisteStats objects
     * @param n: the integer representing top n number of sites
     */
    public static void listTopVisitedSites(Queue<SiteStats> sites, int n) {
        // The logic starts by finding the most visited site and placing it at the rear end of the queue
        // In the next iteration, it finds the 2nd most visited site and places it again at the rear end
        // This process continues, and in the end the least visited site is placed on the rear end
        // and the queue gets fully sorted in the descending order
        for (int i = 0; i < sites.size(); i++) {
            SiteStats mostVisited = findMostVisited(sites, sites.size() - i);
            reorder(sites, mostVisited);
        }
        // Printing the most visited sites up till top 'n'
        System.out.println("Rank | URL | VisitCount");
        for (int i = 0; i < n; i++) {
            // Checking if site is empty before dequeueing process
            if (!sites.isEmpty()) {
                System.out.println(Integer.toString(i + 1) + " | " + sites.remove());
            }
        }
    }
    /**
     * This method finds the website in the queue and increment the visited count by 1,
     * or adds a new node in case website is not found
     * @param url: the String site(or url)
     */
    public static void updateCount(String url) {
        Boolean newSite = true;
        SiteStats updated = null;
        int queueSize = sites.size();
        for (int i = 0; i < queueSize; i++) {
            // Remove element for processing
            SiteStats head = sites.remove();

            // If the urls are equal
            if (head.getUrl().equals(url)) {
                // Update the numVisits by 1
                head.setNumVisits(head.getNumVisits() + 1);
                // Mark the boolean variable newSite as false, as this website is not new
                newSite = false;
                // Insert element after processing
                updated = head;
            }
            // Insert element after processing
            else{
                sites.add(head);
            }
        }

        // Insert new element with initialized count if it was not present in the queue
        if (newSite) {
            sites.add(new SiteStats(url, 1));
        }
        else {
            sites.add(updated);
        }




    }
    // Default execution of the program
    public static void main(String[] args) {
        String[] visitedSites = { "www.google.co.in", "www.google.co.in", "www.facebook.com", "www.upgrad.com", "www.google.co.in", "www.youtube.com",
                "www.facebook.com", "www.upgrad.com", "www.facebook.com", "www.google.co.in", "www.microsoft.com", "www.9gag.com", "www.netflix.com",
                "www.netflix.com", "www.9gag.com", "www.microsoft.com", "www.amazon.com", "www.amazon.com", "www.uber.com", "www.amazon.com",
                "www.microsoft.com", "www.upgrad.com" };

        for (String url : visitedSites) {
            updateCount(url);
        }
        listTopVisitedSites(sites, 5);

    }

}

