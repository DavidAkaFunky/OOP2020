package woo.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes
import woo.app.exceptions.FileOpenFailedException;
import woo.exceptions.MissingFileAssociationException;

/**
 * Save current state to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<Storefront> {

  private Input<String> _filename;

  /** @param receiver */
  public DoSave(Storefront receiver) {
    super(Label.SAVE, receiver);
    if (_receiver.getFilename() == null) {
      _filename = _form.addStringInput(Message.newSaveAs());
    }
  }

  /**
   * @throws FileOpenFailedException
   * @see pt.tecnico.po.ui.Command#execute()
   */
  @Override
  public final void execute() {
    try {
      if (_receiver.getFilename() == null) {
        _form.parse();
        _receiver.saveAs(_filename.value());
      }
      else {
        _receiver.save();
      }
    } catch (IOException | MissingFileAssociationException e) {
      e.fillInStackTrace();
    }
  }
}
