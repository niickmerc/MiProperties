package ui;

import model.Portfolio;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Observable;
import java.util.Observer;


public class SummaryTable extends JPanel implements Observer {
    private static final Color BACKGROUND_COLOR = new Color(45, 47, 48);
    private Label totalPortfolioSize;
    private Label totalPortfolioValue;
    private Label totalOccupancyRate;
    private Label monthlyRentalIncome;
    private Portfolio portfolio;

    // EEFECTS: creates a new table to displaying a user's portfolio statistics
    public SummaryTable(Portfolio portfolio) {
        super();
        this.portfolio = portfolio;
        designTableHeaders();
        designTableValues();
        portfolio.addObserver(this);
    }

    // MODIFIES: this
    // EFFECTS: constructs summary panel headers and adds them to the summary panel
    private void designTableHeaders() {
        Box summaryTableHeaders = new Box(BoxLayout.Y_AXIS);
        Label portfolioSizeHeader  = new Label("Total Properties: ");
        summaryTableHeaders.add(portfolioSizeHeader);
        summaryTableHeaders.add(Box.createVerticalStrut(10));
        Label portfolioValueHeader  = new Label("Total Portfolio Value: ");
        summaryTableHeaders.add(portfolioValueHeader);
        summaryTableHeaders.add(Box.createVerticalStrut(10));
        Label occupancyRateHeader = new Label("Occupancy Rate: ");
        summaryTableHeaders.add(occupancyRateHeader);
        summaryTableHeaders.add(Box.createVerticalStrut(10));
        Label totalRentalIncomeHeader  = new Label("Total Rental Income: ");
        summaryTableHeaders.add(totalRentalIncomeHeader);
        add(summaryTableHeaders);
    }

    // MODIFIES: this
    // EFFECTS: constructs summary panel values and adds them to the summary panel
    public void designTableValues() {
        Box summaryTableValues = new Box(BoxLayout.Y_AXIS);
        summaryTableValues.add(totalPortfolioSize = new Label(String.valueOf(portfolio.getPropertyList().size())));
        summaryTableValues.add(Box.createVerticalStrut(10));
        summaryTableValues.add(totalPortfolioValue = new Label(currencyConverter(portfolio.getTotalPortfolioValue())));
        summaryTableValues.add(Box.createVerticalStrut(10));
        summaryTableValues.add(totalOccupancyRate = new Label((portfolio.getOccupancyRate()) + "%"));
        summaryTableValues.add(Box.createVerticalStrut(10));
        summaryTableValues.add(monthlyRentalIncome =  new Label(currencyConverter(portfolio.getTotalMonthlyRent())));
        add(summaryTableValues);
        setBackground(BACKGROUND_COLOR);
    }

    // EFFECTS: updates displayed statistics
    public void refreshSummaryStatistics() {
        totalPortfolioSize.setText(String.valueOf(portfolio.getPropertyList().size()));
        totalPortfolioValue.setText(currencyConverter(portfolio.getTotalPortfolioValue()));
        totalOccupancyRate.setText(portfolio.getOccupancyRate() + "%");
        monthlyRentalIncome.setText(currencyConverter(portfolio.getTotalMonthlyRent()));
    }

    // EFFECTS: converts an integer value to a currency value
    public String currencyConverter(long num) {
        return NumberFormat.getCurrencyInstance().format(num);
    }

    @Override
    // EFFECTS: updates displayed statistics
    public void update(Observable o, Object arg) {
        refreshSummaryStatistics();
    }

    // EFFECTS: reassigns portfolio for statistics purposes
    public void setPortfolio(Portfolio newPortfolio) {
        portfolio = newPortfolio;
    }
}
