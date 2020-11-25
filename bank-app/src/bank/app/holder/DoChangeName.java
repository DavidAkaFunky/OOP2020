package bank.app.holder;

import bank.Holder;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;

/**
 * Classe ChangeName is a command for changing an account holder's name.
 */
public class DoChangeName extends Command<Holder> {

	/**
	 * Constructor.
	 * 
	 * @param holder
	 */
	public DoChangeName(Holder holder) {
		super(Labels.CHANGE_NAME, holder);
	}

  /** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		//TODO (alunos)
	}
}
