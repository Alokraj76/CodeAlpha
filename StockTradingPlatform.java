import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

class MarketData
{
    private Map<String,Double> stockPrices = new HashMap<>();
    private Random random = new Random();

    public MarketData()
    {
        stockPrices.put("AAPL",150.0);
        stockPrices.put("GOOG",2800.0);
        stockPrices.put("MSFT",300.0);
    }
    public void updatePrices()
    {
        for(String stock : stockPrices.keySet())
        {
            double change = (random.nextDouble()-0.5)*5;
            stockPrices.put(stock,stockPrices.get(stock)+change);
        }
    }
    public double getPrice(String stockSymbol)
    {
        return stockPrices.getOrDefault(stockSymbol, 0.0);
    }
    public Map<String,Double> getAllPrices()
    {
        return stockPrices;
    }
}
class Portfolio
{
    private Map<String,Integer> holdings = new HashMap<>();
    private double cash;

    public Portfolio(double initialCash)
    {
        this.cash = initialCash;
    }
    public boolean buyStock(String stockSymbol,int quantity,double price)
    {
        double totalCost = quantity * price;
        if(cash>=totalCost)
        {
            holdings.put(stockSymbol,holdings.getOrDefault(stockSymbol, 0)+quantity);
            cash -= totalCost;
            return true;
        }
        return false;
    }
    public boolean sellStock(String stockSymbol,int quantity,double price)
    {
        if(holdings.getOrDefault(stockSymbol, 0)>=quantity)
        {
            holdings.put(stockSymbol,holdings.get(stockSymbol)-quantity);
            cash +=quantity*price;
            return true;
        }
        return false;
    }
    public double getCash()
    {
        return cash;
    }
    public Map<String,Integer> getHoldings()
    {
        return holdings;
    }
    public double getPortfolioValue(MarketData marketData)
    {
        double totalValue = cash;
        for(Map.Entry<String,Integer> entry : holdings.entrySet())
        {
            totalValue += entry.getValue() * marketData.getPrice(entry.getKey());
        }
        return totalValue;
    }
}

public class StockTradingPlatform 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in); 
        MarketData marketData = new MarketData();
        Portfolio portfolio = new Portfolio(10000);

        while(true)
        {
            System.out.println("\n--- Stock Trading Platform ---");
            System.out.println("1. View Market Data");
            System.out.println("2.Buy Stock");
            System.out.println("3. Sell Stokc");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice)
            {
                case 1 -> {
                    marketData.updatePrices();
                    System.out.println("Market Data: " + marketData.getAllPrices());
                }
                case 2 -> {
                    System.out.println("Enter stock symbol: ");
                    String buySymbol = sc.nextLine();
                    System.out.println("Enter Quantity");
                    int buyQuantity = sc.nextInt();
                    sc.nextLine();
                    if(portfolio.buyStock(buySymbol,buyQuantity,marketData.getPrice(buySymbol)))
                    {
                        System.out.println("Purchase successfull: ");
                    }
                    else
                    {
                        System.out.println("Not Enough cash");
                    }
                }
                case 3 -> {
                    System.out.println("Enter stock symbol:");
                    String sellSymbol = sc.nextLine();
                    System.out.println("Enter quantity:");
                    int sellQuantity = sc.nextInt();
                    sc.nextLine();
                    if(portfolio.sellStock(sellSymbol, sellQuantity, marketData.getPrice(sellSymbol)))
                    {
                        System.out.println("Sale successfull: ");
                    }
                    else
                    {
                        System.out.println("Not enough stock:");
                    }
                }
                case 4 -> {
                    System.out.println("Portfolio:"+portfolio.getHoldings());
                    System.out.println("Cash: $"+ portfolio.getCash());
                    System.out.println("Portfolio Value: $"+portfolio.getPortfolioValue(marketData));
                }
                case 5 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice: Try again");
            }
        }
        
    }
}
