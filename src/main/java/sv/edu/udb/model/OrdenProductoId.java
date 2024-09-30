package sv.edu.udb.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrdenProductoId implements Serializable {

    private Long orden;
    private Long producto;

    public OrdenProductoId() {}

    public OrdenProductoId(Long orden, Long producto) {
        this.orden = orden;
        this.producto = producto;
    }

    // Getters and Setters
    public Long getOrden() {
        return orden;
    }

    public void setOrden(Long orden) {
        this.orden = orden;
    }

    public Long getProducto() {
        return producto;
    }

    public void setProducto(Long producto) {
        this.producto = producto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrdenProductoId)) return false;
        OrdenProductoId that = (OrdenProductoId) o;
        return Objects.equals(orden, that.orden) && Objects.equals(producto, that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orden, producto);
    }
}
