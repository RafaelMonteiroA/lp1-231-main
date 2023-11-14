package semana24;

public class Cardápio {
    private Integer id;
    private String preco;
    private String saborPizza;

    public Cardápio(Integer id, String preco, String saborPizza) {
        this.id = id;
        this.preco = preco;
        this.saborPizza = saborPizza;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getsaborPizza() {
        return saborPizza;
    }

    public void setsaborPizza(String saborPizza) {
        this.saborPizza = saborPizza;
    }

    @Override
    public String toString() {
        return "Cardápio [id=" + id + ", preço=" + preco + ", sabor_pizza=" + saborPizza + "]";
    }

    

}