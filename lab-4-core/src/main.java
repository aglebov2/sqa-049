import jdk.dynalink.Operation;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

class hierarchy {
    public static void main(String[] args) {
        System.out.println("Hello, hierarchy");
        operation.main1();
        valut.main2();
    }
    static class operation {
        public enum OperType {
            SALE,
            BYE
        }
        private OperType operType;
        private String name;
        private Emmitent emmitent;
        public void Operation(OperType operType, String name, Emmitent emmitent) {
            this.operType = operType;
            this.name = name;
            this.emmitent = emmitent;
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
        public class Journal {
            private List<Operation> operations;
            public Journal() {
                operations = new ArrayList<Operation>();
            }

            public List<Operation> getOperations() {
                return operations;
            }

        }



            public static void main1() {
            System.out.println("Hello, operation");
        }

    }
    static class valut {
        public static void main2() {
            System.out.println("Hello, valut");
        }
        public class Currency {
            private String name;
            private Number value;
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

            public Currency(String name, Number value) {
                this.name = name;
                this.value = value;
            }
        }
    }

    public class Stock {
        private long number;
        public Stock(long number) {
            this.number = number;
        }

        public long getNumber() {
            return number;
        }

        public void setNumber(long number) {
            this.number = number;
        }
        private Emmitent emmitent;

        public Stock(long number, Emmitent emmitent) {
            this.number = number;
            this.emmitent = emmitent;
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
        public Market(String name, List<Currency> currencyList) {
            this.name = name;
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
        private Emmitent emmitent;

        public Market(String name, Emmitent emmitent, List<Currency> currencyList) {
            this.name = name;
            this.emmitent = emmitent;
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
        public Emmitent(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        private Stock stock;
        private List<Market> marketList;

        public Emmitent(String name, Stock stock, List<Market> marketList) {
            this.name = name;
            this.stock = stock;
            this.marketList = marketList;
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
