import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.util.*;
import java.util.List;

public class SimpleUI extends JFrame {
    public SimpleUI() {
        super("Simple UI Example"); // 타이틀 설정
        setSize(300, 200); // 윈도우 크기 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 조건 설정
        setLayout(new FlowLayout()); // 레이아웃 설정

        AddConvertButton();
        addDropArea();

        setVisible(true); // 윈도우 보이게 설정
    }
    private void AddConvertButton() {
        JButton button = new JButton("Convert"); // 버튼 생성
        add(button); // 버튼을 프레임에 추가
    }
    
    private void addDropArea() {
        JLabel dropArea = new JLabel("Drop PNG file here", SwingConstants.CENTER);
        dropArea.setPreferredSize(new Dimension(350, 150)); // 크기 설정
        dropArea.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 테두리 설정
        dropArea.setOpaque(true);
        dropArea.setBackground(Color.LIGHT_GRAY); // 배경 색 설정

        // 드래그 앤 드롭 기능 설정
        new DropTarget(dropArea, new DropTargetListener() {
            public void dragEnter(DropTargetDragEvent dtde) {
                List<File> files = getFileList(dtde);
                if (files.isEmpty()) {
                    dtde.rejectDrag();
                } else {
                    dtde.acceptDrag(DnDConstants.ACTION_COPY);
                }
            }

            public void dragOver(DropTargetDragEvent dtde) { }

            public void dropActionChanged(DropTargetDragEvent dtde) { }

            public void dragExit(DropTargetEvent dte) { }

            public void drop(DropTargetDropEvent dtde) {

                List<File> files = getFileList(dtde);

                try {
                    if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                        dtde.acceptDrop(DnDConstants.ACTION_COPY);
                        @SuppressWarnings("unchecked")
                        List<File> droppedFiles = (List<File>) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                        // 여기에 파일 처리 로직 추가
                        JOptionPane.showMessageDialog(null, "File dropped: " + droppedFiles.get(0).getAbsolutePath());
                    } else {
                        dtde.rejectDrop();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dtde.dropComplete(true);
            }

            private List<File> getFileList(DropTargetDragEvent dtde) {
                try {                    
                    if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                        return (List<File>) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    }
                } catch (Exception ignore) {

                }
                return new ArrayList<>();
            }
        });

        add(dropArea); // 레이블을 프레임에 추가
    }

    public static void main(String[] args) {
        new SimpleUI(); // UI 실행
    }
}