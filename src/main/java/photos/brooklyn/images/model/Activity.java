package photos.brooklyn.images.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Activity {
    @JsonProperty("SettlementDate")
    private Date settlementDate;
    @JsonProperty("Account")
    private String account;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("QuantitySpecified")
    private boolean quantitySpecified;
    @JsonProperty("SymbolCusip")
    private String symbolCusip;
    @JsonProperty("Quantity")
    private int quantity;
    public Date getSettlementDate() {
        return settlementDate;
    }
    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isQuantitySpecified() {
        return quantitySpecified;
    }
    public void setQuantitySpecified(boolean quantitySpecified) {
        this.quantitySpecified = quantitySpecified;
    }
    public String getSymbolCusip() {
        return symbolCusip;
    }
    public void setSymbolCusip(String symbolCusip) {
        this.symbolCusip = symbolCusip;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
