import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

class Hierarchy {
    public static void main(String[] args) {
        System.out.println("Hello, hierarchy");
        Operation.main1();
        Valut.main2();
    }

    static class Operation {
        private OperType operType;
        private String name;
        private Emmitent emmitent;

        public Operation(OperType operType, String name, Emmitent emmitent) {
            this.operType = operType;
            this.name = name;
            this.emmitent = emmitent;
        }

        public static void main1() {
            System.out.println("Hello, operation");
        }

        public OperType getOperType() {
            return operType;
        }

        public void setOperType(OperType operType) {
            this.operType = operType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Emmitent getEmmitent() {
            return emmitent;
        }

        public void setEmmitent(Emmitent emmitent) {
            this.emmitent = emmitent;
        }

        public enum OperType {
            SALE,
            BUY
        }

        public class Journal {
            private List<Operation> operations;

            public Journal() {
                operations = new ArrayList<>();
            }

            public List<Operation> getOperations() {
                return operations;
            }

        }

    }

    static class Valut {
        public static void main2() {
            System.out.println("Hello, valut");
        }

        public class Currency {
            private String name;
            private Number value;

            public Currency(String name, Number value) {
                this.name = name;
                this.value = value;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Number getValue() {
                return value;
            }

            public void setValue(Number value) {
                this.value = value;
            }
        }
    }

    public class Stock {
        private long number;
        private Emmitent emmitent;

        public Stock(long number) {
            this.number = number;
        }

        public Stock(long number, Emmitent emmitent) {
            this.number = number;
            this.emmitent = emmitent;
        }

        public long getNumber() {
            return number;
        }

        public void setNumber(long number) {
            this.number = number;
        }

        public Emmitent getEmmitent() {
            return emmitent;
        }

        public void setEmmitent(Emmitent emmitent) {
            this.emmitent = emmitent;
        }


    }

    public class Market {
        private String name;
        private List<Currency> currencyList;
        private Emmitent emmitent;

        public Market(String name, List<Currency> currencyList) {
            this.name = name;
            this.currencyList = currencyList;
        }

        public Market(String name, Emmitent emmitent, List<Currency> currencyList) {
            this.name = name;
            this.emmitent = emmitent;
            this.currencyList = currencyList;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Currency> getCurrencyList() {
            return currencyList;
        }

        public void setCurrencyList(List<Currency> currencyList) {
            this.currencyList = currencyList;
        }

        public Emmitent getEmmitent() {
            return emmitent;
        }

        public void setEmmitent(Emmitent emmitent) {
            this.emmitent = emmitent;
        }


    }

    public class Emmitent {
        private String name;
        private Stock stock;
        private List<Market> marketList;

        public Emmitent(String name) {
            this.name = name;
        }

        public Emmitent(String name, Stock stock, List<Market> marketList) {
            this.name = name;
            this.stock = stock;
            this.marketList = marketList;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Stock getStock() {
            return stock;
        }

        public void setStock(Stock stock) {
            this.stock = stock;
        }

        public List<Market> getMarketList() {
            return marketList;
        }

        public void setMarketList(List<Market> marketList) {
            this.marketList = marketList;
        }


    }


}
