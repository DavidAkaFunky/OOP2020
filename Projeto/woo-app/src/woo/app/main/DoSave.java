package woo.app.main;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes
import woo.app.exceptions.FileOpenFailedException;

/**
 * Save current state to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<Storefront> {

  private Input<String> _filename;

  /** @param receiver */
  public DoSave(Storefront receiver) {
    super(Label.SAVE, receiver);
    if (_receiver.getFilename() == null) {
      _form.addStringInput(Message.newSaveAs());
    }
  }

  /**
   * @throws FileOpenFailedException
   * @see pt.tecnico.po.ui.Command#execute()
   */
  @Override
  public final void execute() throws FileOpenFailedException {
    try {
      if (_receiver.getFilename() == null) {
        _form.parse();
        _receiver.saveAs(_filename.value());
      }
      _receiver.save();
    } catch (Exception e) {
      /* what exception to throw ? */
      throw new FileOpenFailedException(_filename.value());
    }
  }
}
