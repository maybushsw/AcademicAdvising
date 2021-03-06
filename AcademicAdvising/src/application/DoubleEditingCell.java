package application;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import account.Student;



public class DoubleEditingCell extends TableCell<Student,Double> {
	
    private TextField textField;
    
    
    public DoubleEditingCell() {
    }
    
    //Override start edit function of tablecell to create a texfield to edit
    @Override
    public void startEdit() {
        super.startEdit();
        if (textField == null) {
            createTextField();
        }
        setGraphic(textField);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                textField.requestFocus();
                textField.selectAll();
            }
        });
        
      
    }
    
    
    //cancel edit function override in table cell
    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText( getItem().toString());
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }
    
    
    //Override updateItem of tableCell to update the texfield
    @Override
    public void updateItem(Double item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setGraphic(textField);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            } else {
                setText(getString());
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            }
        }
    }
    
    //Private helper function to create a text field that is edited on mouse double click
    private void createTextField() {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @SuppressWarnings("unchecked")
			@Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {
                    commitEdit(Double.valueOf(textField.getText()));
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                } else if (t.getCode() == KeyCode.TAB) {
                    commitEdit(Double.valueOf(textField.getText()));
                    @SuppressWarnings("rawtypes")
					TableColumn nextColumn = getNextColumn(!t.isShiftDown());
                    if (nextColumn != null) {
                        getTableView().edit(getTableRow().getIndex(), nextColumn);
                    }
                }
            }
        });
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue && textField != null) {
                    commitEdit(Double.valueOf(textField.getText()));
                }
            }
        });
    }
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
       
        
        
        //Private method for tabbing next
        private TableColumn<Student, ?> getNextColumn(boolean forward) {
            List<TableColumn<Student, ?>> columns = new ArrayList<>();
            for (TableColumn<Student, ?> column : getTableView().getColumns()) {
                columns.addAll(getLeaves(column));
            }
           
            if (columns.size() < 2) {
                return null;
            }
            int currentIndex = columns.indexOf(getTableColumn());
            int nextIndex = currentIndex;
            if (forward) {
                nextIndex++;
                if (nextIndex > columns.size() - 1) {
                    nextIndex = 0;
                }
            } else {
                nextIndex--;
                if (nextIndex < 0) {
                    nextIndex = columns.size() - 1;
                }
            }
            return columns.get(nextIndex);
        }
        
        //private helper method
        private List<TableColumn<Student, ?>> getLeaves(TableColumn<Student, ?> root) {
            List<TableColumn<Student, ?>> columns = new ArrayList<>();
            if (root.getColumns().isEmpty()) {
                
                if (root.isEditable()) {
                    columns.add(root);
                }
                return columns;
            } else {
                for (TableColumn<Student, ?> column : root.getColumns()) {
                    columns.addAll(getLeaves(column));
                }
                return columns;
            }
        }
    }


