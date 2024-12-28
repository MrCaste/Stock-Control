package com.stockcontrol.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.stockcontrol.modelo.Product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class StockController implements Initializable {

    @FXML
    private TableColumn<Product, Integer> tablaIdentificador;

    @FXML
    private TableColumn<Product, String> tablaFecha;

    @FXML
    private TableView<Product> tableView;

    @FXML
    private TableColumn<Product, String> tablaProducto;

    @FXML
    private TableColumn<Product, Integer> tablaStock;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField textFieldIdentificador;

    @FXML
    private TextField textFieldProducto;

    @FXML
    private TextField textFieldStock;

    @FXML
    private TextField textFieldFiltro;

    private ObservableList<Product> list = FXCollections.observableArrayList();
    private FilteredList<Product> filterList;

    @FXML
    void addStock(ActionEvent event) {
        String textIdentificator = textFieldIdentificador.getText();
        String textProduct = textFieldProducto.getText();
        String textStock = textFieldStock.getText();
        if(textStock.isEmpty() || textProduct.isEmpty() || textIdentificator.isEmpty()){
            ControllerTransitions.showErrorAlert("Error", "Todos los campos son requeridos");
        } else {
            Product product = new Product(Integer.parseInt(textFieldIdentificador.getText()), textFieldProducto.getText(), Integer.parseInt(textFieldStock.getText()), datePicker.getValue());
            list.add(product);
        }
        textFieldIdentificador.clear();
        textFieldProducto.clear();
        textFieldStock.clear();
    }

    @FXML
    void copyOnFields(MouseEvent event) {
        Product product = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
        textFieldProducto.setText(product.getName());
        textFieldStock.setText(String.valueOf(product.getStock()));
    }

    @FXML
    void modifyStock(ActionEvent event) {
        Product product = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
        int index = tableView.getSelectionModel().getSelectedIndex();
        Product productModify = new Product(product.getIdentificator(), textFieldProducto.getText(), Integer.parseInt(textFieldStock.getText()), datePicker.getValue());
        list.set(index, productModify);
        textFieldProducto.clear();
        textFieldStock.clear();
    }

    @FXML
    void filterProduct(KeyEvent event) {
        filterList.setPredicate(product -> filter(product));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /******Inicializamos la tabla******/
        tablaIdentificador.setCellValueFactory(new PropertyValueFactory<Product, Integer>("identificator"));
        tablaProducto.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        tablaStock.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        tablaFecha.setCellValueFactory(new PropertyValueFactory<Product, String>("fecha"));
        filterList = new FilteredList<>(list, predicate -> true);
        tableView.setItems(filterList); 
    }

    public boolean filter(Product product){
        String filterText = textFieldFiltro.getText();
        if(filterText.isEmpty()){
            return true;
        }
        return String.valueOf(product.getIdentificator()).toLowerCase().contains(filterText.toLowerCase());
    }

}
