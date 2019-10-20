package app.printer;

import app.model.Etiqueta;

import java.util.List;

public interface TagPrinter {

    String printTagA(List<Etiqueta> etiquetas);
    String printTagB(List<Etiqueta> etiquetas);

}
