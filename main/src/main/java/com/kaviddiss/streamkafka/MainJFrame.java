/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaviddiss.streamkafka;

import com.kaviddiss.streamkafka.model.ArticleDAO;
import com.kaviddiss.streamkafka.model.CategoryDAO;
import com.kaviddiss.streamkafka.model.User;
import com.kaviddiss.streamkafka.service.GreetingsController;
import com.kaviddiss.streamkafka.service.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

/**
 *
 * @author bass
 */
@Slf4j
@SpringBootApplication
public class MainJFrame extends javax.swing.JFrame {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GreetingsController greetingsController;

    /**
     * Creates new form NewJFrame
     */
    public MainJFrame() {
        initComponents();
    }

    public void start() {
        User user = this.userRepository.findOne(1L);
        log.info(user.toString());
    }

    public void fillArtList(java.util.List<ArticleDAO> articles) {
        jPanel8.removeAll();
        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);

        GroupLayout.ParallelGroup pg = jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        GroupLayout.SequentialGroup sg = jPanel8Layout.createSequentialGroup();

        for(ArticleDAO art : articles) {
            JPanel jPanelArtList = new javax.swing.JPanel();
            JLabel jLabelListArtId = new javax.swing.JLabel();
            JLabel jLabelListArtTitle = new javax.swing.JLabel();
            JLabel jLabelListArtData = new javax.swing.JLabel();
            JLabel jLabelListArtCatName = new javax.swing.JLabel();
            JLabel jLabelListArtBody = new javax.swing.JLabel();
            JLabel jLabelListArtCatId = new javax.swing.JLabel();
            JLabel jLabelListArtEdit = new javax.swing.JLabel();
            JLabel jLabelListArtDelete = new javax.swing.JLabel();

            jLabelListArtId.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabelListArtId.setText(art.getId().toString());

            jLabelListArtTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jLabelListArtTitle.setText(art.getTitle());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
            jLabelListArtData.setText(dateFormat.format(art.getCreated()));

            jLabelListArtCatName.setText("---");

            jLabelListArtBody.setText("<html><p>" + art.getBody() + "</p></html>");
            jLabelListArtBody.setVerticalAlignment(javax.swing.SwingConstants.TOP);

            jLabelListArtCatId.setText(art.getCatId().toString());

            jLabelListArtEdit.setText("<HTML><FONT color='#000099'><U>edit</U></FONT></HTML>");
            jLabelListArtEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jLabelListArtEdit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);

                    jTextFieldArtId.setText(art.getId().toString());
                    jTextFieldArtCatId.setText(art.getCatId().toString());
                    jTextFieldArtTitle.setText(art.getTitle());
                    jTextPaneArtBody.setText(art.getBody());

                    log.info("edit article " + art.getId());
                }
            });

            jLabelListArtDelete.setText("<HTML><FONT color='#000099'><U>delete</U></FONT></HTML>");
            jLabelListArtDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jLabelListArtDelete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);

                    if (!showConfirmDialog("Подтвердите удаление статьи '" + art.getTitle()  + "'")) {
                        return;
                    }

                    greetingsController.createUpdateArt(art.getId(), art.getCatId(), art.getUserId(), art.getTitle(), art.getBody(), true);

                    log.info("delete article " + art.getId());
                }
            });

            javax.swing.GroupLayout jPanelArtListLayout = new javax.swing.GroupLayout(jPanelArtList);
            jPanelArtList.setLayout(jPanelArtListLayout);
            jPanelArtListLayout.setHorizontalGroup(
                    jPanelArtListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelArtListLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanelArtListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelListArtBody, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanelArtListLayout.createSequentialGroup()
                                                    .addComponent(jLabelListArtId)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jLabelListArtTitle))
                                            .addGroup(jPanelArtListLayout.createSequentialGroup()
                                                    .addComponent(jLabelListArtData)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jLabelListArtCatName)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabelListArtCatId))
                                            .addGroup(jPanelArtListLayout.createSequentialGroup()
                                                    .addComponent(jLabelListArtEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jLabelListArtDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap())
            );
            jPanelArtListLayout.setVerticalGroup(
                    jPanelArtListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelArtListLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanelArtListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabelListArtId)
                                            .addComponent(jLabelListArtTitle))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanelArtListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabelListArtData)
                                            .addComponent(jLabelListArtCatName)
                                            .addComponent(jLabelListArtCatId))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabelListArtBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanelArtListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabelListArtEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelListArtDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            pg.addComponent(jPanelArtList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
            sg.addComponent(jPanelArtList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
            sg.addGap(0, 10, Short.MAX_VALUE);
        }

        jPanel8Layout.setHorizontalGroup(pg);
        jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(sg)
        );
    }

    public void fillCatList(java.util.List<CategoryDAO> categories) {
        jPanelCatList.removeAll();
        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanelCatList);
        jPanelCatList.setLayout(jPanel6Layout);

        GroupLayout.ParallelGroup pgHoriz = jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        GroupLayout.SequentialGroup sgVert = jPanel6Layout.createSequentialGroup();

        for (final CategoryDAO cat : categories) {
            JPanel jPanelCat = new javax.swing.JPanel();
            JLabel jLabelListCatId = new javax.swing.JLabel();
            JLabel jLabelListCatName = new javax.swing.JLabel();
            JLabel jLabelListCatEdit = new javax.swing.JLabel();
            JLabel jLabelListCatDelete = new javax.swing.JLabel();

            jLabelListCatId.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabelListCatId.setText(cat.getId().toString());

            jLabelListCatName.setText("<HTML><FONT color='#000099'><U>" + cat.getName() + "</U></FONT></HTML>");
            jLabelListCatName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jLabelListCatName.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);

                    greetingsController.getArtFromCat(cat.getId());

                    log.info("show all articles from " + cat.getName());
                }
            });

            jLabelListCatEdit.setText("<HTML><FONT color='#000099'><U>e</U></FONT></HTML>");
            jLabelListCatEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jLabelListCatEdit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);

                    jTextFieldCatId.setText(cat.getId().toString());
                    jTextFieldCatName.setText(cat.getName());

                    log.info("edit category " + cat.getName());
                }
            });

            jLabelListCatDelete.setText("<HTML><FONT color='#000099'><U>d</U></FONT></HTML>");
            jLabelListCatDelete.setToolTipText("");
            jLabelListCatDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jLabelListCatDelete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);

                    if (!showConfirmDialog("Подтвердите удаление категории '" + cat.getName()  + "'")) {
                        return;
                    }

                    greetingsController.createUpdateCat(cat.getId(), null, true);

                    log.info("delete category " + cat.getName());
                }
            });

            javax.swing.GroupLayout jPanelCatListLayout = new javax.swing.GroupLayout(jPanelCat);
            jPanelCat.setLayout(jPanelCatListLayout);

            jPanelCatListLayout.setHorizontalGroup(
                    jPanelCatListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCatListLayout.createSequentialGroup()
                                    .addComponent(jLabelListCatId)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabelListCatDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabelListCatEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabelListCatName, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                    .addContainerGap())
            );
            jPanelCatListLayout.setVerticalGroup(
                    jPanelCatListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCatListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelListCatId)
                                    .addComponent(jLabelListCatEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelListCatDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelListCatName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            pgHoriz.addComponent(jPanelCat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
            sgVert.addComponent(jPanelCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED);

        }

        pgHoriz.addComponent(jPanelAllCategories, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        sgVert.addComponent(jPanelAllCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED);

        jPanel6Layout.setHorizontalGroup(pgHoriz);
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(sgVert)
        );

//        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanelCatList = new javax.swing.JPanel();
        jPanelAllCategories = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jTextFieldCatId = new javax.swing.JTextField();
        jLabelCatId = new javax.swing.JLabel();
        jTextFieldCatName = new javax.swing.JTextField();
        jLabelCatName = new javax.swing.JLabel();
        jButtonCatEdit = new javax.swing.JButton();
        jLabelCatInfo = new javax.swing.JLabel();
        jButtonCatLoad = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabelArtId = new javax.swing.JLabel();
        jLabelArtCatId = new javax.swing.JLabel();
        jLabelArtTitle = new javax.swing.JLabel();
        jLabelArtBody = new javax.swing.JLabel();
        jTextFieldArtId = new javax.swing.JTextField();
        jTextFieldArtCatId = new javax.swing.JTextField();
        jTextFieldArtTitle = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPaneArtBody = new javax.swing.JTextPane();
        jButtonEditArt = new javax.swing.JButton();
        jLabelArtInfo = new javax.swing.JLabel();
        jLabelAllCategories = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanelAllCategories.setForeground(new java.awt.Color(51, 51, 255));

        jLabelAllCategories.setForeground(new java.awt.Color(51, 51, 255));
        jLabelAllCategories.setText("<HTML><FONT color='#000099'><U>All Categories</U></FONT></HTML>");
        jLabelAllCategories.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAllCategories.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                greetingsController.getAllArt();

                log.info("show articles from all categories");
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanelAllCategories);
        jPanelAllCategories.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabelAllCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelAllCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanelCatList);
        jPanelCatList.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanelAllCategories, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        )
                                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanelAllCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addContainerGap(520, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanelCatList);

        jLabelCatId.setText("Cat ID:");

        jLabelCatName.setText("Cat Name:");

        jButtonCatEdit.setText("Edit/Add");
        jButtonCatEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCatEditActionPerformed(evt);
            }
        });

        jLabelCatInfo.setForeground(new java.awt.Color(255, 51, 51));
        jLabelCatInfo.setText("Cat info...");
        jLabelCatInfo.setToolTipText("");

        jButtonCatLoad.setText("Load Cat");
        jButtonCatLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCatLoadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabelCatInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabelCatId)
                                                        .addComponent(jLabelCatName))
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(jTextFieldCatId, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addComponent(jTextFieldCatName)))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButtonCatEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonCatLoad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldCatId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelCatId)
                                        .addComponent(jButtonCatEdit))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldCatName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelCatName))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelCatInfo)
                                        .addComponent(jButtonCatLoad))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        );
        jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup())
        );

        jScrollPane2.setViewportView(jPanel8);

        jLabelArtId.setText("Art ID:");

        jLabelArtCatId.setText("Cat ID:");

        jLabelArtTitle.setText("Title:");

        jLabelArtBody.setText("Body:");

        jScrollPane3.setViewportView(jTextPaneArtBody);

        jButtonEditArt.setText("Edit/Add");
        jButtonEditArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditArtActionPerformed(evt);
            }
        });

        jLabelArtInfo.setForeground(new java.awt.Color(255, 51, 51));
        jLabelArtInfo.setText("Art info...");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelArtInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                                        .addComponent(jLabelArtCatId)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(jTextFieldArtCatId))
                                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                                        .addComponent(jLabelArtId)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(jTextFieldArtId, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(jButtonEditArt))
                                                .addGap(44, 44, 44)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                .addComponent(jLabelArtBody)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                .addComponent(jLabelArtTitle)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jTextFieldArtTitle)))))
                                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelArtId)
                                        .addComponent(jLabelArtTitle)
                                        .addComponent(jTextFieldArtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldArtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabelArtCatId)
                                                        .addComponent(jLabelArtBody)
                                                        .addComponent(jTextFieldArtCatId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(jButtonEditArt))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabelArtInfo)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCatEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCatEditActionPerformed
        Long id = null;
        if (!jTextFieldCatId.getText().isEmpty()) {
            id = Long.decode(jTextFieldCatId.getText());
        }
        if(jTextFieldCatName.getText().isEmpty()) {
            this.setCatInfo("Не указано название категории");
            return;
        }
        this.greetingsController.createUpdateCat(id, jTextFieldCatName.getText(), false);
    }//GEN-LAST:event_jButtonCatEditActionPerformed

    private void jButtonEditArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditArtActionPerformed
        Long id = null;
        Long catId = null;
        if (!jTextFieldArtId.getText().isEmpty()) {
            id = Long.decode(jTextFieldArtId.getText());
        }
        if(jTextFieldArtCatId.getText().isEmpty()) {
            this.setCatInfo("Не указана категория");
            return;
        }
        catId = Long.decode(jTextFieldArtCatId.getText());

        if(jTextFieldArtTitle.getText().isEmpty()) {
            this.setCatInfo("Не указан заголовок");
            return;
        }
        if(jTextPaneArtBody.getText().isEmpty()) {
            this.setCatInfo("Не введён текст");
            return;
        }

        greetingsController.createUpdateArt(id, catId, 1L, jTextFieldArtTitle.getText(), jTextPaneArtBody.getText(), false);

    }//GEN-LAST:event_jButtonEditArtActionPerformed

    private void jButtonCatLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCatLoadActionPerformed
        this.greetingsController.getAllCat();
    }//GEN-LAST:event_jButtonCatLoadActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(MainJFrame.class)
                .headless(false).run(args);

        EventQueue.invokeLater(() -> {
            MainJFrame ex = ctx.getBean(MainJFrame.class);
            ex.setVisible(true);
            ex.start();
        });
    }

    public void clearCatForm() {
        jTextFieldCatId.setText("");
        jTextFieldCatName.setText("");
    }

    public void clearArtForm() {
        jTextFieldArtId.setText("");
        jTextFieldArtCatId.setText("");
        jTextFieldArtTitle.setText("");
        jTextPaneArtBody.setText("");
    }

    public boolean showConfirmDialog(String message) {
        int dialogResult = JOptionPane.showConfirmDialog(this, message, "Подтверждение", JOptionPane.YES_NO_OPTION);
        if(dialogResult == 0) {
            return true;
        }
        return false;
    }

    public void clearArtList() {
        this.fillArtList(new java.util.ArrayList<>());
    }

    public void setCatInfo(String message) {
        jLabelCatInfo.setText(message);
    }

    public void setArtInfo(String message) {
        jLabelArtInfo.setText(message);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCatEdit;
    private javax.swing.JButton jButtonCatLoad;
    private javax.swing.JButton jButtonEditArt;
    private javax.swing.JLabel jLabelAllCategories;
    private javax.swing.JLabel jLabelArtBody;
    private javax.swing.JLabel jLabelArtCatId;
    private javax.swing.JLabel jLabelArtId;
    private javax.swing.JLabel jLabelArtInfo;
    private javax.swing.JLabel jLabelArtTitle;
    private javax.swing.JLabel jLabelCatId;
    private javax.swing.JLabel jLabelCatInfo;
    private javax.swing.JLabel jLabelCatName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelCatList;
    private javax.swing.JPanel jPanelAllCategories;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextFieldArtCatId;
    private javax.swing.JTextField jTextFieldArtId;
    private javax.swing.JTextField jTextFieldArtTitle;
    private javax.swing.JTextField jTextFieldCatId;
    private javax.swing.JTextField jTextFieldCatName;
    private javax.swing.JTextPane jTextPaneArtBody;
    // End of variables declaration//GEN-END:variables
}
