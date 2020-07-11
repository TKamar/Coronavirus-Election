package Program;

import java.time.LocalDate;
import java.util.Random;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.xml.ws.handler.MessageContext.Scope;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class View implements ElectionUIEvent {
	private viewListener listener;
	private BorderPane bpMain = new BorderPane();
	private GridPane gpRoot = new GridPane();

	public View(Stage primaryStage) {
		Label lblTitle = new Label("Election Round " + LocalDate.now().getDayOfMonth() + "/" 
				+ LocalDate.now().getMonthValue() +"/" + LocalDate.now().getYear());
		bpMain.setTop(lblTitle);
		bpMain.setAlignment(lblTitle, Pos.CENTER);
		lblTitle.setFont(Font.font("Verdana", 17));
		lblTitle.setUnderline(true);
//		Color.
		lblTitle.setBackground(new Background(new BackgroundFill(Paint.valueOf("STEELBLUE"), CornerRadii.EMPTY, new Insets(0))));
		bpMain.setBackground(new Background(new BackgroundFill(Paint.valueOf("LIGHTBLUE"), CornerRadii.EMPTY, new Insets(0))));
		bpMain.setPadding(new Insets(10));
		bpMain.setAlignment(bpMain, Pos.CENTER_LEFT);
		Button btnAddBallot = new Button("Add ballot box");
		Button btnAddCitizen = new Button("Add citizen");
		Button btnAddParty = new Button("Add party");
		Button btnAddPartyMem = new Button("Add party member");
		Button btnShowBallots = new Button("Show all ballot box");
		Button btnShowCitizens = new Button("Show all citizen");
		Button btnShowParties = new Button("Show all parties");
		Button btnElectionRun = new Button("Vote");
		Button btnResults = new Button("Show results");
		Button btnExit = new Button("Exit");

		gpRoot.add(btnAddBallot, 0, 0);
		gpRoot.add(btnAddCitizen, 0, 1);
		gpRoot.add(btnAddParty, 0, 2);
		gpRoot.add(btnAddPartyMem, 0, 3);
		gpRoot.add(btnShowBallots, 0, 4);
		gpRoot.add(btnShowCitizens, 0, 5);
		gpRoot.add(btnShowParties, 0, 6);
		gpRoot.add(btnElectionRun, 0, 7);
		gpRoot.add(btnResults, 0, 8);
		gpRoot.add(btnExit, 0, 9);
		gpRoot.setVgap(5);

		btnAddBallot.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				bpMain.setCenter(null);
				bpMain.setRight(null);
				GridPane gpFormFill = new GridPane();
				Button btnFinish = new Button("Finish");
				ComboBox<String> cbBallotType = new ComboBox<String>();
				Label lblChooseBallotType = new Label("choose from the folowing:");
				cbBallotType.getItems().addAll("1 ---> Regular ballot box", "2 ---> Corona ballot box",
						"3 ---> Military ballot box", "4---> Military Corona ballot box");
				Label lblAddress = new Label("Enter address: city, street, number");
				TextField tfAddress = new TextField();
				Label lblName = new Label("Enter name");
				TextField tfName = new TextField();

				Label lblId = new Label("Enter id");
				TextField tfId = new TextField();

				Label lblBirthYear = new Label("Enter your birth year");
				TextField tfBirthYear = new TextField();

				Label lblIsolation = new Label("Enter if the citizen in isolation T/F");
				TextField tfIsolation = new TextField();
				Label lblNumOfSickDays = new Label(
						"If the Citizen have corona enter number of sick days, else enter 0");
				TextField tfNumOfSickDays = new TextField();

				gpFormFill.add(lblChooseBallotType, 0, 0);
				gpFormFill.add(cbBallotType, 1, 0);
				gpFormFill.add(lblAddress, 0, 1);
				gpFormFill.add(tfAddress, 1, 1);
				gpFormFill.add(new Label("you must enter voter"), 0, 2);
				gpFormFill.add(lblName, 0, 3);
				gpFormFill.add(tfName, 1, 3);
				gpFormFill.add(lblId, 0, 4);
				gpFormFill.add(tfId, 1, 4);
				gpFormFill.add(lblBirthYear, 0, 5);
				gpFormFill.add(tfBirthYear, 1, 5);
				gpFormFill.add(lblIsolation, 0, 6);
				gpFormFill.add(tfIsolation, 1, 6);
				gpFormFill.add(lblNumOfSickDays, 0, 7);
				gpFormFill.add(tfNumOfSickDays, 1, 7);

				gpFormFill.add(btnFinish, 0, 9, 2, 2);
				gpFormFill.setVgap(5);
				gpFormFill.setPadding(new Insets(15));
				gpFormFill.setHgap(10);
				bpMain.setCenter(gpFormFill);

				btnFinish.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						gpFormFill.setVisible(false);
						int choice = Integer.parseInt(cbBallotType.getValue().charAt(0) + "");
						Citizen c = new Citizen();
						Address address = new Address("", "", 0);

						String[] inputs = tfAddress.getText().split(", ");
						String city = inputs[0];
						String street = inputs[1];
						int number = Integer.parseInt(inputs[2]);
						address = new Address(street, city, number);
						String name = tfName.getText();
						int id = Integer.parseInt(tfId.getText());
						boolean flag = false;
						while (!flag) {
							try {
								c.setId(id);
								flag = true;
							} catch (Exception e) {
								tfId.setText("");
								JOptionPane.showMessageDialog(null, e.getMessage());
								id = Integer.parseInt(JOptionPane.showInputDialog("Enter again:"));
							}
						}

						int birthYear = Integer.parseInt(tfBirthYear.getText());
						flag = false;
						while (!flag) {
							try {
								c.setBirthYear(birthYear);
								flag = true;
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, e.getMessage());
								birthYear = Integer.parseInt(JOptionPane.showInputDialog("Enter again:"));
							}
						}
						String isolation = tfIsolation.getText();
						boolean isIsolated;
						if (isolation.equalsIgnoreCase("t"))
							isIsolated = true;
						else
							isIsolated = false;

						int numberOfSickDays = Integer.parseInt(tfNumOfSickDays.getText());
						if (LocalDate.now().getYear() - birthYear >= 18
								&& LocalDate.now().getYear() - birthYear <= 21) {
							boolean isCarryWeapon= new Random().nextBoolean();
							if (numberOfSickDays > 0) {
								try {
									c = new SickSoldier(name, id,
											checkRelatedBallotBox(birthYear, numberOfSickDays, getAllBallotBox()),
											isIsolated, birthYear, isCarryWeapon, numberOfSickDays);
								} catch (Exception e) {
									JOptionPane.showMessageDialog(null, e.getMessage());
								}
							} else {
								try {
									c = new Soldier(name, id,
											checkRelatedBallotBox(birthYear, numberOfSickDays, getAllBallotBox()),
											isIsolated, birthYear, isCarryWeapon);
								} catch (Exception e) {
									JOptionPane.showMessageDialog(null, e.getMessage());
								}
							}
						} else {
							if (numberOfSickDays > 0) {
								try {
									c = new SickCitizen(name, id,
											checkRelatedBallotBox(birthYear, numberOfSickDays, getAllBallotBox()),
											isIsolated, birthYear, numberOfSickDays);
								} catch (Exception e) {
									JOptionPane.showMessageDialog(null, e.getMessage());
								}
							} else {
								try {
									c = new Citizen(name, id,
											checkRelatedBallotBox(birthYear, numberOfSickDays, getAllBallotBox()),
											isIsolated, birthYear);
								} catch (Exception e) {
									JOptionPane.showMessageDialog(null, e.getMessage());
								}
							}
						}
						Vector<Citizen> allVoters1 = new Vector<Citizen>();
						Vector<SickCitizen> allVoters2 = new Vector<SickCitizen>();
						Vector<Soldier> allVoters3 = new Vector<Soldier>();
						Vector<SickSoldier> allVoters4 = new Vector<SickSoldier>();

						switch (choice) {
						case 1:
							allVoters1.add(c);
							break;

						case 2:
							allVoters2.add((SickCitizen) c);
							break;

						case 3:
							allVoters3.add((Soldier) c);
							break;

						case 4:
							allVoters4.add((SickSoldier) c);
							break;
						}

						switch (choice) {
						case 1:
							listener.addBallotBox(new BallotBox<Citizen>(address, new ElectionRes(), allVoters1));
							break;

						case 2:
							listener.addBallotBox(new BallotBox<SickCitizen>(address, new ElectionRes(), allVoters2));
							break;

						case 3:
							listener.addBallotBox(new BallotBox<Soldier>(address, new ElectionRes(), allVoters3));
							break;

						case 4:
							listener.addBallotBox(new BallotBox<SickSoldier>(address, new ElectionRes(), allVoters4));
							break;
						}
						try {
							listener.addCitizen(c);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					}
				});
			}
		});

		btnAddCitizen.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				bpMain.setCenter(null);
				bpMain.setRight(null);
				GridPane gpFormFill = new GridPane();
				Button btnFinish = new Button("Finish");
				Label lblName = new Label("Enter name");
				TextField tfName = new TextField();

				Label lblId = new Label("Enter id");
				TextField tfId = new TextField();

				Label lblBirthYear = new Label("Enter your birth year");
				TextField tfBirthYear = new TextField();

				Label lblIsolation = new Label("Enter if the citizen in isolation T/F");
				TextField tfIsolation = new TextField();
				Label lblNumOfSickDays = new Label(
						"If the Citizen have corona enter number of sick days, else enter 0");
				TextField tfNumOfSickDays = new TextField();

				gpFormFill.add(lblName, 0, 0);
				gpFormFill.add(tfName, 1, 0);
				gpFormFill.add(lblId, 0, 1);
				gpFormFill.add(tfId, 1, 1);
				gpFormFill.add(lblBirthYear, 0, 2);
				gpFormFill.add(tfBirthYear, 1, 2);
				gpFormFill.add(lblIsolation, 0, 3);
				gpFormFill.add(tfIsolation, 1, 3);
				gpFormFill.add(lblNumOfSickDays, 0, 4);
				gpFormFill.add(tfNumOfSickDays, 1, 4);

				gpFormFill.add(btnFinish, 0, 6, 2, 2);
				bpMain.setCenter(gpFormFill);
				gpFormFill.setVgap(5);
				gpFormFill.setHgap(10);
				gpFormFill.setPadding(new Insets(15));


				btnFinish.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						gpFormFill.setVisible(false);
						String name = tfName.getText();
						int id = Integer.parseInt(tfId.getText());
						boolean flag = false;
						Citizen c = new Citizen();
						while (!flag) {
							try {
								c.setId(id);
								flag = true;
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, e.getMessage());
								id = Integer.parseInt(JOptionPane.showInputDialog("Enter again:"));
							}
						}

						int birthYear = Integer.parseInt(tfBirthYear.getText());
						flag = false;
						while (!flag) {
							try {
								c.setBirthYear(birthYear);
								flag = true;
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, e.getMessage());
								birthYear = Integer.parseInt(JOptionPane.showInputDialog("Enter again:"));
							}
						}
						String isolation = tfIsolation.getText();
						boolean isIsolated;
						if (isolation.equalsIgnoreCase("t"))
							isIsolated = true;
						else
							isIsolated = false;

						int numberOfSickDays = Integer.parseInt(tfNumOfSickDays.getText());
						if (LocalDate.now().getYear() - birthYear >= 18
								&& LocalDate.now().getYear() - birthYear <= 21) {

							boolean isCarryWeapon = new Random().nextBoolean();

							if (numberOfSickDays > 0) {
								try {
									c = new SickSoldier(name, id,
											checkRelatedBallotBox(birthYear, numberOfSickDays, getAllBallotBox()),
											isIsolated, birthYear, isCarryWeapon, numberOfSickDays);
									listener.addCitizen(c);
								} catch (Exception e) {
									JOptionPane.showMessageDialog(null, e.getMessage());
								}
							} else {
								try {
									c = new Soldier(name, id,
											checkRelatedBallotBox(birthYear, numberOfSickDays, getAllBallotBox()),
											isIsolated, birthYear, isCarryWeapon);
									listener.addCitizen(c);
								} catch (Exception e) {
									JOptionPane.showMessageDialog(null, e.getMessage());
								}
							}
						} else {
							if (numberOfSickDays > 0) {
								try {
									c = new SickCitizen(name, id,
											checkRelatedBallotBox(birthYear, numberOfSickDays, getAllBallotBox()),
											isIsolated, birthYear, numberOfSickDays);
									listener.addCitizen(c);
								} catch (Exception e) {
									JOptionPane.showMessageDialog(null, e.getMessage());
								}
							} else {
								try {
									c = new Citizen(name, id,
											checkRelatedBallotBox(birthYear, numberOfSickDays, getAllBallotBox()),
											isIsolated, birthYear);
									listener.addCitizen(c);
								} catch (Exception e) {
									JOptionPane.showMessageDialog(null, e.getMessage());
								}
							}
						}
					}
				});
			}
		});

		btnAddParty.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				bpMain.setCenter(null);
				bpMain.setRight(null);
				GridPane gpFormFill = new GridPane();
				Button btnFinish = new Button("Finish");
				Label lblPartyName = new Label("Enter Party name");
				TextField tfPartyName = new TextField();
				Label lblPartySection = new Label("Enter party section");
				TextField tfPartySection = new TextField();
				Label lblOpeningDate = new Label("Enter party opening date- year, month, day");
				TextField tfOpeningDate = new TextField();
				Label lblName = new Label("Enter name");
				TextField tfName = new TextField();
				Label lblId = new Label("Enter id");
				TextField tfId = new TextField();
				Label lblBirthYear = new Label("Enter your birth year");
				TextField tfBirthYear = new TextField();
				Label lblIsolation = new Label("Enter if the citizen in isolation T/F");
				TextField tfIsolation = new TextField();
				Label lblPlaceInParty = new Label("Enter place in party");
				TextField tfPlaceInParty = new TextField();

				gpFormFill.add(lblPartyName, 0, 0);
				gpFormFill.add(tfPartyName, 1, 0);
				gpFormFill.add(lblPartySection, 0, 1);
				gpFormFill.add(tfPartySection, 1, 1);
				gpFormFill.add(lblOpeningDate, 0, 2);
				gpFormFill.add(tfOpeningDate, 1, 2);
				gpFormFill.add(new Label("Add party member to party"), 0, 3);
				gpFormFill.add(lblName, 0, 4);
				gpFormFill.add(tfName, 1, 4);
				gpFormFill.add(lblId, 0, 5);
				gpFormFill.add(tfId, 1, 5);
				gpFormFill.add(lblBirthYear, 0, 6);
				gpFormFill.add(tfBirthYear, 1, 6);
				gpFormFill.add(lblIsolation, 0, 7);
				gpFormFill.add(tfIsolation, 1, 7);
				gpFormFill.add(lblPlaceInParty, 0, 8);
				gpFormFill.add(tfPlaceInParty, 1, 8);

				gpFormFill.add(btnFinish, 0, 9, 2, 2);
				bpMain.setCenter(gpFormFill);
				gpFormFill.setVgap(5);
				gpFormFill.setHgap(10);
				gpFormFill.setPadding(new Insets(15));


				btnFinish.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						gpFormFill.setVisible(false);
						String partyName = tfPartyName.getText();
						String section = tfPartySection.getText();
						String[] inputs = tfOpeningDate.getText().split(", ");
						int year = Integer.parseInt(inputs[0]);
						int month = Integer.parseInt(inputs[1]);
						int day = Integer.parseInt(inputs[2]);
						Date openingDate = new Date(year, month, day);
						Party p1 = new Party<Citizen>(partyName, section, openingDate);
						String name = tfName.getText();
						int id = Integer.parseInt(tfId.getText());
						boolean flag = false;
						Citizen c = new Citizen();
						while (!flag) {
							try {
								c.setId(id);
								flag = true;
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, e.getMessage());
								id = Integer.parseInt(JOptionPane.showInputDialog("Enter again:"));
							}
						}

						int birthYear = Integer.parseInt(tfBirthYear.getText());
						flag = false;
						while (!flag) {
							try {
								c.setBirthYear(birthYear);
								flag = true;
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, e.getMessage());
								birthYear = Integer.parseInt(JOptionPane.showInputDialog("Enter again:"));
							}
						}
						int placeInParty = Integer.parseInt(tfPlaceInParty.getText());
						String isolation = tfIsolation.getText();
						boolean isIsolated;
						if (isolation.equalsIgnoreCase("t"))
							isIsolated = true;
						else
							isIsolated = false;
						try {
							c = new PartyMember(name, id, checkRelatedBallotBox(birthYear, 0, getAllBallotBox()),
									isIsolated, birthYear, p1, placeInParty);
							listener.addCitizen(c);
							listener.addParty(p1);
							p1.addPartyMember(c);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					}
				});
			}
		});

		btnAddPartyMem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				bpMain.setCenter(null);
				bpMain.setRight(null);
				GridPane gpFormFill = new GridPane();
				Button btnFinish = new Button("Finish");
				Label lblName = new Label("Enter name");
				TextField tfName = new TextField();
				Label lblId = new Label("Enter id");
				TextField tfId = new TextField();
				Label lblBirthYear = new Label("Enter your birth year");
				TextField tfBirthYear = new TextField();
				Label lblIsolation = new Label("Enter if the citizen in isolation T/F");
				TextField tfIsolation = new TextField();
				Label lblParty = new Label("Enter party");
				TextField tfParty = new TextField();
				Label lblPlaceInParty = new Label("Enter place in party");
				TextField tfPlaceInParty = new TextField();

				gpFormFill.add(lblName, 0, 0);
				gpFormFill.add(tfName, 1, 0);
				gpFormFill.add(lblId, 0, 1);
				gpFormFill.add(tfId, 1, 1);
				gpFormFill.add(lblBirthYear, 0, 2);
				gpFormFill.add(tfBirthYear, 1, 2);
				gpFormFill.add(lblIsolation, 0, 3);
				gpFormFill.add(tfIsolation, 1, 3);
				gpFormFill.add(lblParty, 0, 4);
				gpFormFill.add(tfParty, 1, 4);
				gpFormFill.add(lblPlaceInParty, 0, 5);
				gpFormFill.add(tfPlaceInParty, 1, 5);

				gpFormFill.add(btnFinish, 0, 7, 2, 2);
				gpFormFill.setVgap(5);
				gpFormFill.setHgap(10);
				gpFormFill.setPadding(new Insets(15));
				bpMain.setCenter(gpFormFill);
				
				btnFinish.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						gpFormFill.setVisible(false);
						String name = tfName.getText();
						int id = Integer.parseInt(tfId.getText());
						boolean flag = false;
						Citizen c = new Citizen();
						while (!flag) {
							try {
								c.setId(id);
								flag = true;
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, e.getMessage());
								id = Integer.parseInt(JOptionPane.showInputDialog("Enter again:"));
							}
						}

						int birthYear = Integer.parseInt(tfBirthYear.getText());
						flag = false;
						while (!flag) {
							try {
								c.setBirthYear(birthYear);
								flag = true;
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, e.getMessage());
								birthYear = Integer.parseInt(JOptionPane.showInputDialog("Enter again:"));
							}
						}
						String PartyName = tfParty.getText();
						flag = false;
						Party p1 = new Party<Citizen>("", "", new Date(0, 0, 0));
						while (!flag) {
							for (int i = 0; i < getAllParties().size(); i++) {
								if (PartyName.equalsIgnoreCase(getAllParties().get(i).getName()))
									flag = true;
								p1 = getAllParties().get(i);
							}
							if (!flag) {
								JOptionPane.showMessageDialog(null, "Invalid party name");
								PartyName = JOptionPane.showInputDialog("Enter again:");
							}
						}
						int placeInParty = Integer.parseInt(tfPlaceInParty.getText());
						String isolation = tfIsolation.getText();
						boolean isIsolated;
						if (isolation.equalsIgnoreCase("t"))
							isIsolated = true;
						else
							isIsolated = false;
						try {
							c = new PartyMember(name, id, checkRelatedBallotBox(birthYear, 0, getAllBallotBox()),
									isIsolated, birthYear, p1, placeInParty);
							listener.addCitizen(c);
							p1.addPartyMember(c);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					}
				});
			}

		});

		btnShowBallots.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				bpMain.setRight(null);
				listener.viewAskedShowAllBallotBox();
			}
		});

		btnShowCitizens.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				bpMain.setRight(null);
				listener.viewAskedShowAllCitizen();
			}
		});

		btnShowParties.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				bpMain.setRight(null);
				listener.viewAskedShowAllParties();
			}
		});

		btnElectionRun.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				listener.electionRun();
			}
		});

		btnResults.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				bpMain.setRight(null);
				listener.viewAskedForResults();
			}
		});

		btnExit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				bpMain.setVisible(false);
				JOptionPane.showMessageDialog(null, "Good luck with your new country");
			}
		});

		bpMain.setLeft(gpRoot);

		Scene scene = new Scene(bpMain, 800, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private static BallotBox<Citizen> checkRelatedBallotBox(int birthYear, int numberOfSickDays,
			Vector<BallotBox<? extends Citizen>> allBallotBox) {
		for (int i = 0; i < allBallotBox.size(); i++) {
			if (LocalDate.now().getYear() - birthYear <= 21 && LocalDate.now().getYear() - birthYear >= 18) {
				if (numberOfSickDays > 0) {
					if (allBallotBox.get(i).getBallotBoxType() == SickSoldier.class) {
						return (BallotBox<Citizen>) allBallotBox.get(i);
					}
				} else if (allBallotBox.get(i).getBallotBoxType() == Soldier.class) {
					return (BallotBox<Citizen>) allBallotBox.get(i);
				}
			} else if (numberOfSickDays > 0) {
				if (allBallotBox.get(i).getBallotBoxType() == SickCitizen.class) {
					return (BallotBox<Citizen>) allBallotBox.get(i);
				}
			} else
				return (BallotBox<Citizen>) allBallotBox.get(i);
		}
		return null;
	}

	private Vector<Party> getAllParties() {
		return listener.getAllParties();
	}

	private Vector<BallotBox<? extends Citizen>> getAllBallotBox() {
		return listener.getAllBallotBox();
	}

	@Override
	public void registerListener(viewListener l) {
		listener = l;
	}

	@Override
	public void showAllBallotBox(String output) {
		bpMain.setCenter(null);
		TextArea tf = new TextArea();
		tf.setText(output);
		bpMain.setRight(tf);
	}

	@Override
	public void showAllCitizen(String output) {
		bpMain.setCenter(null);
		TextArea tf = new TextArea();
		tf.setText(output);
		bpMain.setRight(tf);
	}

	@Override
	public void showAllParties(String output) {
		bpMain.setCenter(null);
		TextArea tf = new TextArea();
		tf.setText(output);
		bpMain.setRight(tf);
	}

	public void showResults(String output) {
		bpMain.setCenter(null);
		TextArea tf = new TextArea();
		tf.setText(output);
		bpMain.setRight(tf);
	}

	@Override
	public void results(String output) {
		bpMain.setCenter(null);
		TextArea tf = new TextArea();
		tf.setText(output);
		bpMain.setRight(tf);
	}

	@Override
	public void electionRunMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

}
