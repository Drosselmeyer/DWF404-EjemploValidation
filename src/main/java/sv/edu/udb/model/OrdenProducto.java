package sv.edu.udb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orden_producto")
public class OrdenProducto {

    @EmbeddedId
    private OrdenProductoId id;

    @ManyToOne
    @MapsId("orden")
    @JoinColumn(name = "orden_id", nullable = false)
    private Orden orden;

    @ManyToOne
    @MapsId("producto")
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private int cantidad;

    // Constructores
    public OrdenProducto() {}

    public OrdenProducto(Orden orden, Producto producto, int cantidad) {
        this.orden = orden;
        this.producto = producto;
        this.cantidad = cantidad;
        this.id = new OrdenProductoId(orden.getId(), producto.getId());
    }

    // Getters y Setters
    public OrdenProductoId getId() {
        return id;
    }

    public void setId(OrdenProductoId id) {
        this.id = id;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
        this.id.setOrden(orden.getId());
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
        this.id.setProducto(producto.getId());
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
