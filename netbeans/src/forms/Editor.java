package forms;
import gui.ProjectManager;
import gui.*;
import java.io.File;
import model.*;

/**
 * Janela principal da edicao da visual novel.
 * Possui componentes para todas operacoes.
 * @author Estevan
 */
public class Editor extends javax.swing.JFrame {

    public ProjectManager projectManager;
    public SceneSelector sceneSelector;
    public SceneEditor sceneEditor;
    public MediaImporter mediaImporter;
    Tree tree = new Tree();

    public Editor() {
        initComponents();  
        onEditorStart();
    }
    
    /**
     * Inicializa variaveis do ambiente do editor, quando ele eh aberto.
     */
    private void onEditorStart()
    {
        tree.createTree(MyTree);        
        LabelProjectName.setText("");    
        projectManager = new ProjectManager(this, MenuProjectExport, MenuProjectSave);
        sceneSelector = new SceneSelector(this, ListScene, ButtonNewScene, ButtonDeleteScene);
        sceneEditor = new SceneEditor(this, TextAreaScene, ComboBoxSprite);
        mediaImporter = new MediaImporter(this);
        setSceneEditorListeners();        
        setMediaButtonsListeners();
    }
    
    /**
     * Operacoes para quando um projeto eh criado ou aberto.
     * Exemplos: Habilitar botoes.
     * @param newProject Projeto aberto.
     */
    public void onProjectStart(Project newProject)
    {        
        // Atualizar nome do projeto.
        LabelProjectName.setText(newProject.gameName);
        // Habilitar botoes da SceneManager.
        ButtonNewScene.setEnabled(true);
        ButtonDeleteScene.setEnabled(true); 
        if (newProject.getSceneCount() > 0)
        {
            // Habilitar area de texto da cena.
            TextAreaScene.setEnabled(true);
            // Habilitar botoes de edicao de cena.
            setSceneEditorButtonsEnabled(true);
        }
        TextAreaScene.setText("");
        // Habilitar botoes da midia.
        setMediaButtonsEnabled(true);
        // Managers.
        sceneSelector.updateList(newProject);
        // Árvore.
        tree.createTree(MyTree);   
    }
        
    /**
     * Ativa ou nao botoes de edicao da cena.
     * @param active Ativo.
     */
    public void setSceneEditorButtonsEnabled(boolean active)
    {
        ButtonAddDialogue.setEnabled(active);
        ButtonChangeBG.setEnabled(active);
        ButtonChangeMusic.setEnabled(active);
        ButtonAddSprite.setEnabled(active);
        ButtonRemoveSprite.setEnabled(active);
        ButtonChoose.setEnabled(active);
        ButtonJumpScene.setEnabled(active);
        ComboBoxSprite.setEnabled(active);
    }
    
    /**
     * Ativa os listeners de botoes da edicao da cena.
     */
    private void setSceneEditorListeners()
    {
        ButtonAddDialogue.addActionListener(sceneEditor);
        ButtonAddDialogue.setActionCommand("Dialogue");
        ButtonChangeBG.addActionListener(sceneEditor);
        ButtonChangeBG.setActionCommand("Change BG");
        ButtonChangeMusic.addActionListener(sceneEditor);
        ButtonChangeMusic.setActionCommand("Change Music");
        ButtonAddSprite.addActionListener(sceneEditor);
        ButtonAddSprite.setActionCommand("Add Sprite");
        ButtonRemoveSprite.addActionListener(sceneEditor);
        ButtonRemoveSprite.setActionCommand("Remove Sprite");
        ButtonChoose.addActionListener(sceneEditor);
        ButtonChoose.setActionCommand("Choose");
        ButtonJumpScene.addActionListener(sceneEditor);
        ButtonJumpScene.setActionCommand("Jump");
    }
    
    /**
     * Ativa ou desativa botoes da midia.
     * @param active Ativo
     */
    private void setMediaButtonsEnabled(boolean active)
    {
        ButtonImportCG.setEnabled(active);
        ButtonImportBG.setEnabled(active);
        ButtonImportMusic.setEnabled(active);
        ButtonImportSprite.setEnabled(active);
    }
    
    /**
     * Ativa os listeners dos botoes da midia.
     */
    private void setMediaButtonsListeners()
    {
        ButtonImportBG.addActionListener(mediaImporter);
        ButtonImportBG.setActionCommand("BG");
        ButtonImportCG.addActionListener(mediaImporter);
        ButtonImportCG.setActionCommand("CG");
        ButtonImportMusic.addActionListener(mediaImporter);
        ButtonImportMusic.setActionCommand("Music");
        ButtonImportSprite.addActionListener(mediaImporter);
        ButtonImportSprite.setActionCommand("Sprite");
    }
    
    /**
     * Callback da janela de selecao do nome do projeto.
     * @param projectName Nome do projeto.
     */
    public void onWindowProjectNameAccept(String projectName)
    {        
        projectManager.onProjectCreate(projectName);
        this.setEnabled(true);    
    }    
    
    /**
     * Callback da janela de selecao do nome do projeto. 
     */
    public void onWindowProjectNameCancel()
    {
        this.setEnabled(true);    
    }
    
    /**
     * Callback da janela de selecao de arquivo.     
     * @param target Arquivo selecionado.
     */
    public void onFileSelected(File target)
    {
        projectManager.onProjectLoad(target);
        this.setEnabled(true);  
    }
    
    /**
     * Callback da janela de selecao de arquivo.          
     */
    public void onFileNotSelected()
    {
        this.setEnabled(true);
    } 
    
    /**
     * Atualiza a label que mostra o estado da cena,
     * se esta salva ou nao.
     */
    public void updateSceneSavedState()
    {
        String output = "Arquivo de Cena";
        if (projectManager.getCurrentScene() != null && !projectManager.getCurrentScene().isSaved())
            output += " (nao salvo)";
        
        PanelSceneText.setBorder(javax.swing.BorderFactory.createTitledBorder(output));
    }
    
    /**
     * Cria uma janela para mostrar uma mensagem.
     * @param message Mensagem.
     */
    public void showWindowMessage(String message)
    {
        ShowMessage.main(this, message);
        this.setEnabled(false);  
    }    
    
    /**
     * Callback para fim da janela de mensagem.
     */
    public void onWindowMessageEnd()
    {
        this.setEnabled(true);  
    }
    
    /**
     * Callback para quando qualquer tipo de midia eh importada.
     */
    public void onMediaImported()
    {
        // Atualiza a árvore.
        tree.createTree(MyTree); 
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelBase = new javax.swing.JPanel();
        LabelProjectName = new javax.swing.JLabel();
        PanelSceneManager = new javax.swing.JPanel();
        ButtonNewScene = new javax.swing.JButton();
        ButtonDeleteScene = new javax.swing.JButton();
        ScrollPaneScene = new javax.swing.JScrollPane();
        ListScene = new javax.swing.JList();
        PanelImport = new javax.swing.JPanel();
        ButtonImportBG = new javax.swing.JButton();
        ButtonImportSprite = new javax.swing.JButton();
        ButtonImportCG = new javax.swing.JButton();
        ButtonImportMusic = new javax.swing.JButton();
        PanelTree = new javax.swing.JPanel();
        ScrollPaneTree = new javax.swing.JScrollPane();
        MyTree = new javax.swing.JTree();
        PanelSceneText = new javax.swing.JPanel();
        ScrollPaneSceneText = new javax.swing.JScrollPane();
        TextAreaScene = new javax.swing.JTextArea();
        PanelSceneEdition = new javax.swing.JPanel();
        ButtonAddDialogue = new javax.swing.JButton();
        ButtonChangeBG = new javax.swing.JButton();
        ButtonChangeMusic = new javax.swing.JButton();
        ButtonJumpScene = new javax.swing.JButton();
        ButtonChoose = new javax.swing.JButton();
        ButtonRemoveSprite = new javax.swing.JButton();
        ButtonAddSprite = new javax.swing.JButton();
        ComboBoxSprite = new javax.swing.JComboBox();
        Separator1 = new javax.swing.JSeparator();
        Separator2 = new javax.swing.JSeparator();
        MenuBar = new javax.swing.JMenuBar();
        MenuProject = new javax.swing.JMenu();
        MenuProjectCreate = new javax.swing.JMenuItem();
        MenuProjectLoad = new javax.swing.JMenuItem();
        MenuProjectExport = new javax.swing.JMenuItem();
        MenuProjectSave = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Visual Novel - Editor");
        setMinimumSize(new java.awt.Dimension(500, 300));
        setResizable(false);

        PanelBase.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        LabelProjectName.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        LabelProjectName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelProjectName.setText("Danganronpa: Happy Trigger Havoc");
        LabelProjectName.setBorder(javax.swing.BorderFactory.createTitledBorder("Nome"));

        PanelSceneManager.setBorder(javax.swing.BorderFactory.createTitledBorder("Cenas"));

        ButtonNewScene.setText("Nova Cena");
        ButtonNewScene.setEnabled(false);
        ButtonNewScene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonNewSceneActionPerformed(evt);
            }
        });

        ButtonDeleteScene.setText("Deletar Cena");
        ButtonDeleteScene.setEnabled(false);
        ButtonDeleteScene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDeleteSceneActionPerformed(evt);
            }
        });

        ScrollPaneScene.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneScene.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        ListScene.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        ListScene.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Cena 1", "Cena 2", "Cena 3", "Cena 4", "Cena 5", "Cena 6", "Cena 7" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        ListScene.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ListScene.setEnabled(false);
        ListScene.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ListSceneValueChanged(evt);
            }
        });
        ScrollPaneScene.setViewportView(ListScene);

        javax.swing.GroupLayout PanelSceneManagerLayout = new javax.swing.GroupLayout(PanelSceneManager);
        PanelSceneManager.setLayout(PanelSceneManagerLayout);
        PanelSceneManagerLayout.setHorizontalGroup(
            PanelSceneManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSceneManagerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSceneManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ButtonNewScene, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonDeleteScene, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ScrollPaneScene, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelSceneManagerLayout.setVerticalGroup(
            PanelSceneManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSceneManagerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ButtonNewScene)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonDeleteScene)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollPaneScene, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelImport.setBorder(javax.swing.BorderFactory.createTitledBorder("Importar"));

        ButtonImportBG.setText("Background");
        ButtonImportBG.setEnabled(false);

        ButtonImportSprite.setText("Sprite");
        ButtonImportSprite.setEnabled(false);

        ButtonImportCG.setText("CG");
        ButtonImportCG.setEnabled(false);

        ButtonImportMusic.setText("Música");
        ButtonImportMusic.setEnabled(false);

        javax.swing.GroupLayout PanelImportLayout = new javax.swing.GroupLayout(PanelImport);
        PanelImport.setLayout(PanelImportLayout);
        PanelImportLayout.setHorizontalGroup(
            PanelImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelImportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonImportBG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonImportSprite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonImportCG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonImportMusic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelImportLayout.setVerticalGroup(
            PanelImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelImportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ButtonImportBG)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonImportSprite)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonImportCG)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonImportMusic)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelTree.setBorder(javax.swing.BorderFactory.createTitledBorder("Arquivos"));

        ScrollPaneTree.setViewportView(MyTree);

        javax.swing.GroupLayout PanelTreeLayout = new javax.swing.GroupLayout(PanelTree);
        PanelTree.setLayout(PanelTreeLayout);
        PanelTreeLayout.setHorizontalGroup(
            PanelTreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTreeLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ScrollPaneTree, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelTreeLayout.setVerticalGroup(
            PanelTreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollPaneTree)
        );

        PanelSceneText.setBorder(javax.swing.BorderFactory.createTitledBorder("Arquivo de Cena"));

        TextAreaScene.setColumns(20);
        TextAreaScene.setRows(5);
        TextAreaScene.setEnabled(false);
        TextAreaScene.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextAreaSceneKeyTyped(evt);
            }
        });
        ScrollPaneSceneText.setViewportView(TextAreaScene);

        javax.swing.GroupLayout PanelSceneTextLayout = new javax.swing.GroupLayout(PanelSceneText);
        PanelSceneText.setLayout(PanelSceneTextLayout);
        PanelSceneTextLayout.setHorizontalGroup(
            PanelSceneTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollPaneSceneText, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
        );
        PanelSceneTextLayout.setVerticalGroup(
            PanelSceneTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollPaneSceneText)
        );

        PanelSceneEdition.setBorder(javax.swing.BorderFactory.createTitledBorder("Edicao da Cena"));

        ButtonAddDialogue.setText("Adicionar Dialogo");
        ButtonAddDialogue.setEnabled(false);

        ButtonChangeBG.setText("Alterar Background");
        ButtonChangeBG.setEnabled(false);

        ButtonChangeMusic.setText("Alterar Musica");
        ButtonChangeMusic.setEnabled(false);

        ButtonJumpScene.setText("Pular Cena");
        ButtonJumpScene.setEnabled(false);

        ButtonChoose.setText("Fazer Escolha");
        ButtonChoose.setEnabled(false);

        ButtonRemoveSprite.setText("Remover Sprite");
        ButtonRemoveSprite.setEnabled(false);

        ButtonAddSprite.setText("Adicionar Sprite");
        ButtonAddSprite.setEnabled(false);

        ComboBoxSprite.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Left", "Center", "Right" }));
        ComboBoxSprite.setEnabled(false);

        javax.swing.GroupLayout PanelSceneEditionLayout = new javax.swing.GroupLayout(PanelSceneEdition);
        PanelSceneEdition.setLayout(PanelSceneEditionLayout);
        PanelSceneEditionLayout.setHorizontalGroup(
            PanelSceneEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSceneEditionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSceneEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSceneEditionLayout.createSequentialGroup()
                        .addGroup(PanelSceneEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ButtonAddDialogue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ButtonChangeMusic, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ButtonChangeBG, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Separator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ButtonAddSprite, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ButtonRemoveSprite, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ComboBoxSprite, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelSceneEditionLayout.createSequentialGroup()
                        .addGroup(PanelSceneEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Separator2)
                            .addComponent(ButtonJumpScene, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ButtonChoose, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        PanelSceneEditionLayout.setVerticalGroup(
            PanelSceneEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSceneEditionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ButtonAddDialogue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonChangeBG)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonChangeMusic)
                .addGap(12, 12, 12)
                .addComponent(Separator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(ButtonAddSprite)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonRemoveSprite)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboBoxSprite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Separator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ButtonJumpScene)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonChoose)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelBaseLayout = new javax.swing.GroupLayout(PanelBase);
        PanelBase.setLayout(PanelBaseLayout);
        PanelBaseLayout.setHorizontalGroup(
            PanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBaseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelProjectName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelBaseLayout.createSequentialGroup()
                        .addGroup(PanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PanelSceneManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PanelImport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelTree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelSceneText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelSceneEdition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PanelBaseLayout.setVerticalGroup(
            PanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBaseLayout.createSequentialGroup()
                .addComponent(LabelProjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelBaseLayout.createSequentialGroup()
                        .addComponent(PanelSceneManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelImport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PanelSceneEdition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelTree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelSceneText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MenuProject.setText("Projeto");

        MenuProjectCreate.setText("Criar");
        MenuProjectCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuProjectCreateActionPerformed(evt);
            }
        });
        MenuProject.add(MenuProjectCreate);

        MenuProjectLoad.setText("Carregar");
        MenuProjectLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuProjectLoadActionPerformed(evt);
            }
        });
        MenuProject.add(MenuProjectLoad);

        MenuProjectExport.setText("Exportar");
        MenuProjectExport.setEnabled(false);
        MenuProjectExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuProjectExportActionPerformed(evt);
            }
        });
        MenuProject.add(MenuProjectExport);

        MenuProjectSave.setText("Salvar Cena Atual");
        MenuProjectSave.setEnabled(false);
        MenuProjectSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuProjectSaveActionPerformed(evt);
            }
        });
        MenuProject.add(MenuProjectSave);

        MenuBar.add(MenuProject);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MenuProjectCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuProjectCreateActionPerformed
        ProjectName.main(this);
        this.setEnabled(false);                  
    }//GEN-LAST:event_MenuProjectCreateActionPerformed

    private void MenuProjectLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuProjectLoadActionPerformed
        FileSelector.main(this);   
        this.setEnabled(false);    
    }//GEN-LAST:event_MenuProjectLoadActionPerformed

    private void MenuProjectExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuProjectExportActionPerformed
        projectManager.onProjectExport();
        tree.createTree(MyTree);
    }//GEN-LAST:event_MenuProjectExportActionPerformed
	
    private void MenuProjectSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuProjectSaveActionPerformed
        projectManager.saveCurrentScene(sceneEditor.getSceneText());  
        updateSceneSavedState();
    }//GEN-LAST:event_MenuProjectSaveActionPerformed

    private void ListSceneValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ListSceneValueChanged
        sceneSelector.onChangeScene();
        updateSceneSavedState();
    }//GEN-LAST:event_ListSceneValueChanged

    private void ButtonDeleteSceneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDeleteSceneActionPerformed
        sceneSelector.onButtonDeleteScene();        
    }//GEN-LAST:event_ButtonDeleteSceneActionPerformed

    private void ButtonNewSceneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonNewSceneActionPerformed
        sceneSelector.onButtonNewScene();                
    }//GEN-LAST:event_ButtonNewSceneActionPerformed

    private void TextAreaSceneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextAreaSceneKeyTyped
        projectManager.setCurrentSceneSavedState(false);
        updateSceneSavedState();
    }//GEN-LAST:event_TextAreaSceneKeyTyped




    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Editor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAddDialogue;
    private javax.swing.JButton ButtonAddSprite;
    private javax.swing.JButton ButtonChangeBG;
    private javax.swing.JButton ButtonChangeMusic;
    private javax.swing.JButton ButtonChoose;
    private javax.swing.JButton ButtonDeleteScene;
    private javax.swing.JButton ButtonImportBG;
    private javax.swing.JButton ButtonImportCG;
    private javax.swing.JButton ButtonImportMusic;
    private javax.swing.JButton ButtonImportSprite;
    private javax.swing.JButton ButtonJumpScene;
    private javax.swing.JButton ButtonNewScene;
    private javax.swing.JButton ButtonRemoveSprite;
    private javax.swing.JComboBox ComboBoxSprite;
    private javax.swing.JLabel LabelProjectName;
    private javax.swing.JList ListScene;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenu MenuProject;
    private javax.swing.JMenuItem MenuProjectCreate;
    private javax.swing.JMenuItem MenuProjectExport;
    private javax.swing.JMenuItem MenuProjectLoad;
    private javax.swing.JMenuItem MenuProjectSave;
    private javax.swing.JTree MyTree;
    private javax.swing.JPanel PanelBase;
    private javax.swing.JPanel PanelImport;
    private javax.swing.JPanel PanelSceneEdition;
    private javax.swing.JPanel PanelSceneManager;
    private javax.swing.JPanel PanelSceneText;
    private javax.swing.JPanel PanelTree;
    private javax.swing.JScrollPane ScrollPaneScene;
    private javax.swing.JScrollPane ScrollPaneSceneText;
    private javax.swing.JScrollPane ScrollPaneTree;
    private javax.swing.JSeparator Separator1;
    private javax.swing.JSeparator Separator2;
    private javax.swing.JTextArea TextAreaScene;
    // End of variables declaration//GEN-END:variables
}
